package com.dnth_underdog_241.online_fashion_shopping.config.report;

import com.dnth_underdog_241.online_fashion_shopping.model.ImportTransaction;
import com.dnth_underdog_241.online_fashion_shopping.repository.ImportTransactionRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PdfReportGenerator {

    private final ImportTransactionRepository importTransactionRepository;
    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload.reports}")
    private String reportDirectory;


    public String generateWeeklyReportTemplate(String fileName)
    {
        try (PdfWriter writer = new PdfWriter(new FileOutputStream(reportDirectory + fileName));
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf))
        {
            PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
            document.setFont(font);

            addTitlePage(document, LocalDate.now());
            addPageBorders(pdf);

            addSalesSectionTemplate(document);
            addInventorySection(document);
            addFinancialPerformanceSectionTemplate(document);
            addHandwritingNoteSection(document);

            return "public/reports/" + fileName;
        } catch (IOException e) {
            System.err.println("Error generating report template: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }


    private void addTitlePage(Document document, LocalDate reportDate)
    {
        String logoPath = "storage/logo.png";
        try {
            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData);
            logo.setWidth(100).setHeight(100).setHorizontalAlignment(HorizontalAlignment.CENTER);
            document.add(logo);
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
        }

        document.add(new Paragraph("ONLINE FASHION SHOPPING")
                .setFontSize(30).setBold().setTextAlignment(TextAlignment.CENTER).setMarginTop(10));

        document.add(new Paragraph("WEEKLY REPORT TEMPLATE")
                .setFontSize(40).setBold().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

        document.add(new Paragraph("Report Date: " + reportDate)
                .setFontSize(14).setTextAlignment(TextAlignment.CENTER));

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    private void addSalesSectionTemplate(Document document)
    {
        document.add(new Paragraph("SALES INFORMATION")
                .setFontSize(24).setBold().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

        Table salesTable = createTableTemplate(new String[]{"Metric", "Details"});
        document.add(salesTable);

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    private void addInventorySection(Document document)
    {
        document.add(new Paragraph("PRODUCT AND INVENTORY DETAILS")
                .setFontSize(24).setBold().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);
        List<ImportTransaction> transactions = importTransactionRepository.findTransactionsInDateRange(startDate, endDate);

        Table inventoryTable = new Table(new float[]{3, 2, 2.5f, 1.5f, 3, 1.5f})
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f));

        // Add a single unified header row with a black background
        Cell headerCell = new Cell(1, 6) // Spans all 6 columns
                .setBackgroundColor(ColorConstants.BLACK)
                .add(new Paragraph("PRODUCT AND INVENTORY DETAILS")
                        .setFontSize(12)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE)
                        .setTextAlignment(TextAlignment.CENTER))
                .setPadding(10)
                .setBorder(Border.NO_BORDER);

        inventoryTable.addHeaderCell(headerCell);

        // Add headers with bold, gray text and black background
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Product Name"));
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Date"));
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Size & Colour"));
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Quantity"));
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Costs (Shipping, Tax, Unit, Total)"));
        inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Status"));

        // Add rows dynamically with alternating row colors and professional padding
        boolean isAlternateRow = false;
        for (ImportTransaction transaction : transactions) {
            Color rowColor = isAlternateRow ? new DeviceRgb(240, 240, 240) : ColorConstants.WHITE;

            inventoryTable.addCell(createLaTeXStyleDataCell(transaction.getVariantProduct().getProduct().getName(), rowColor, TextAlignment.LEFT));
            inventoryTable.addCell(createLaTeXStyleDataCell(transaction.getDate().toString(), rowColor, TextAlignment.CENTER));
            inventoryTable.addCell(createLaTeXStyleDataCell(
                    transaction.getVariantProduct().getSize().getSize().name() + " / " +
                            transaction.getVariantProduct().getColour().getColour().name(),
                    rowColor, TextAlignment.CENTER));
            inventoryTable.addCell(createLaTeXStyleDataCell(String.valueOf(transaction.getQuantity()), rowColor, TextAlignment.RIGHT));

            // Compact cost details with better formatting
            String costDetails = String.format("S: $%.2f | T: $%.2f | U: $%.2f | Tot: $%.2f",
                    transaction.getShippingCost(),
                    transaction.getTax(),
                    transaction.getUnitCost(),
                    transaction.getTotalCost());
            inventoryTable.addCell(createLaTeXStyleDataCell(costDetails, rowColor, TextAlignment.LEFT));

            inventoryTable.addCell(createLaTeXStyleDataCell(transaction.getStatus().toString(), rowColor, TextAlignment.CENTER));
            isAlternateRow = !isAlternateRow;
        }

        // Add subtle divider between sections
        document.add(new LineSeparator(new SolidLine(1)));
        document.add(inventoryTable);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    // Helper method for creating professional column headers in LaTeX-like style
    private Cell createLaTeXStyleColumnHeader(String headerText) {
        return new Cell()
                .add(new Paragraph(headerText)
                        .setFontSize(10)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE))
                .setBackgroundColor(ColorConstants.BLACK)
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(8)
                .setBorder(Border.NO_BORDER);
    }


    // Helper method for data cells with a professional look and feel
    private Cell createLaTeXStyleDataCell(String content, Color backgroundColor, TextAlignment alignment) {
        return new Cell()
                .add(new Paragraph(content)
                        .setFontSize(9))
                .setBackgroundColor(backgroundColor)
                .setTextAlignment(alignment)
                .setPadding(8)
                .setBorder(Border.NO_BORDER);
    }


    private void addFinancialPerformanceSectionTemplate(Document document)
    {
        document.add(new Paragraph("FINANCIAL PERFORMANCE")
                .setFontSize(24).setBold().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

        Table financialTable = createTableTemplate(new String[]{"Metric", "Details"});
        document.add(financialTable);

        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    private void addHandwritingNoteSection(Document document)
    {
        document.add(new Paragraph("NOTES FOR EMPLOYEE AND ADMIN")
                .setFontSize(24).setBold().setTextAlignment(TextAlignment.CENTER).setMarginBottom(20));

        document.add(new Paragraph("Employee Notes: __________________________________________________________________________________________________________")
                .setFontSize(12).setTextAlignment(TextAlignment.LEFT));

        document.add(new Paragraph("Admin Notes: ____________________________________________________________________________________________________________")
                .setFontSize(12).setTextAlignment(TextAlignment.LEFT));
    }


    private Table createTableTemplate(String[] headers) {
        Table table = new Table(headers.length)
                .setWidth(UnitValue.createPercentValue(90))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorder(new SolidBorder(1));

        for (String header : headers) {
            table.addHeaderCell(createHeaderCell(header));
        }

        return table;
    }


    private Cell createHeaderCell(String content) {
        return new Cell()
                .add(new Paragraph(content).setBold().setFontColor(WebColors.getRGBColor("#2C3E50")))
                .setBackgroundColor(WebColors.getRGBColor("#ECF0F1"))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(8);
    }


    private void addPageBorders(PdfDocument pdf) {
        Rectangle pageSize = pdf.getDefaultPageSize();
        PdfCanvas canvas = new PdfCanvas(pdf.getLastPage());
        canvas.setLineWidth(2);
        canvas.rectangle(pageSize.getLeft() + 20, pageSize.getBottom() + 20,
                pageSize.getWidth() - 40, pageSize.getHeight() - 40);
        canvas.stroke();
    }
}
