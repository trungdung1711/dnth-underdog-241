package com.dnth_underdog_241.online_fashion_shopping.config.report;

import com.dnth_underdog_241.online_fashion_shopping.model.ImportTransaction;
import com.dnth_underdog_241.online_fashion_shopping.model.Order;
import com.dnth_underdog_241.online_fashion_shopping.model.systemenum.OrderStatusEnum;
import com.dnth_underdog_241.online_fashion_shopping.repository.ImportTransactionRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.OrderRepository;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.colors.WebColors;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PdfReportGenerator
{

    private final ImportTransactionRepository importTransactionRepository;


    private final OrderRepository orderRepository;


    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload.reports}")
    private String reportDirectory;


    public String generateWeeklyReportTemplate(String fileName)
    {
        try (PdfWriter writer = new PdfWriter(new FileOutputStream(reportDirectory + fileName));
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) 
             {    
                // Set up font
                PdfFont font = PdfFontFactory.createFont(StandardFonts.COURIER);
                document.setFont(font);
    
                // Add content to the document
                addTitlePage(document, LocalDate.now());
                addSalesSection(document);
                addInventorySection(document);
                addFinancialSection(document);
                addHandwritingNoteSection(document);
    
            // Add the border to all pages using a page event (after content)
    
            // Return the path to the generated report
            return "/public/reports/" + fileName;
    
        } 
        catch (IOException e) 
        {
            System.err.println("Error generating report template: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    

    private void addTitlePage(Document document, LocalDate reportDate)
    {
        String logoPath = "storage/logo.png";
        
        // Decorative top block (full-width background bar)
        document.add(new Paragraph()
                .setBackgroundColor(new DeviceRgb(10, 136, 219)) // Professional blue color
                .setHeight(30));
    
        // Add logo (centered under the top decorative bar)
        try {
            ImageData imageData = ImageDataFactory.create(logoPath);
            Image logo = new Image(imageData);
            logo.setWidth(70).setHeight(70).setHorizontalAlignment(HorizontalAlignment.CENTER)
                    .setMarginTop(20);
            document.add(logo);
        } catch (Exception e) {
            System.err.println("Error loading logo: " + e.getMessage());
        }
    
        // Title Section
        document.add(new Paragraph("WEEKLY REPORT")
                .setFontSize(36)
                .setBold() // Bold for emphasis
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(10, 136, 219)) // Dark blue for a professional touch
                .setMarginTop(10));
    
        // Subtitle Section
        document.add(new Paragraph("Online Fashion Shopping (OFS)")
                .setFontSize(18)
                .setBold() // Bold subtitle for better visibility
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(77, 77, 77)) // Neutral gray for subtitles
                .setMarginBottom(20));
    
        // Thin decorative line under the subtitle
        document.add(new LineSeparator(new SolidLine(1))
                .setStrokeColor(new DeviceRgb(10, 136, 219)) // Same professional blue as top bar
                .setMarginBottom(30));
    
        // Contact Information Section
        document.add(new Paragraph("Website: https://ofs.com")
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(51, 51, 51)) // Dark gray for contact info
                .setMarginBottom(5));
        document.add(new Paragraph("Contact: +84 123 456 789")
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(51, 51, 51))
                .setMarginBottom(5));
        document.add(new Paragraph("Address: 268 Ly Thuong Kiet, District 10, Ho Chi Minh City, Vietnam")
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(51, 51, 51))
                .setMarginBottom(20));
        document.add(new Paragraph("University: HCMUT - Ho Chi Minh City University of Technology")
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(51, 51, 51))
                .setMarginBottom(20));
        document.add(new Paragraph("Team Name: dnth_underdog_241")
                .setFontSize(12)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(51, 51, 51))
                .setMarginBottom(40));
    
        // Bottom decorative block (full-width background bar)
        document.add(new Paragraph()
                .setBackgroundColor(new DeviceRgb(10, 136, 219)) // Matches the top bar
                .setHeight(30)
                .setMarginTop(50));
    
        // Footer Section
        document.add(new Paragraph("© 2030 Online Fashion Shopping (OFS)")
                .setFontSize(10)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(77, 77, 77))
                .setMarginTop(10));
    
        // Add a page break after the title page
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    private void addSalesSection(Document document)
    {
        // Title style (consistent with title page)
        document.add(new Paragraph("SALES INFORMATION")
                .setFontSize(24) // Same font size as in the title page
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20)
                .setFontColor(new DeviceRgb(30, 30, 30))); // Dark text, matching the title page

        // Get the current date (today is Monday)
        LocalDate today = LocalDate.now();

        // Calculate the start of the previous week (last Monday)
        LocalDate startOfLastWeek = today.minusWeeks(1).with(DayOfWeek.MONDAY);

        // Calculate the end of the previous week (last Sunday)
        LocalDate endOfLastWeek = startOfLastWeek.plusDays(6); // Sunday of the same week

        // Add a small text for the date range
        document.add(new Paragraph("Sale Period: " + startOfLastWeek + " to " + endOfLastWeek)
                .setFontSize(10)
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(new DeviceRgb(120, 120, 120)) // Lighter gray text
                .setMarginBottom(20));

        // Sample order data retrieval for the previous week - Replace with real data from your repository
        List<Order> orders = orderRepository.findAll(); // Replace with your repository method

        // Create the sales table with defined column widths
        Table salesTable = new Table(new float[]{16.6f, 16.6f, 16.6f, 16.6f, 16.6f, 16.6f})
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(new DeviceRgb(180, 180, 180), 0.5f)); // Subtle gray border to match title page


        // First row: Sale Information header (spanning all columns)
        Cell headerCell = new Cell(1, 6) // Spans all 6 columns
                .setBackgroundColor(new DeviceRgb(10, 136, 219)) // Dark blue background for header
                .add(new Paragraph("SALES INFORMATION")
                        .setFontSize(12)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE)
                        .setTextAlignment(TextAlignment.CENTER))
                .setPadding(10)
                .setBorder(Border.NO_BORDER);
        salesTable.addHeaderCell(headerCell);

        // Second row: Actual column headers (e.g., Order ID, Paypal ID, etc.)
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Order ID"));
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Paypal ID"));
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Status"));
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Time"));
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Customer"));
        salesTable.addHeaderCell(createLaTeXStyleColumnHeader("Total Items"));

        // Add rows dynamically with alternating row colors and professional padding
        boolean isAlternateRow = false;
        for (Order order : orders) {
            Color rowColor = isAlternateRow ? new DeviceRgb(245, 245, 245) : ColorConstants.WHITE;

            salesTable.addCell(createLaTeXStyleDataCell(String.valueOf(order.getId()), rowColor, TextAlignment.CENTER));
            salesTable.addCell(createLaTeXStyleDataCell((order.getPaypalId() != null) ? order.getPaypalId() : "N/A", rowColor, TextAlignment.CENTER));
            salesTable.addCell(createLaTeXStyleDataCell(order.getOrderStatus().toString(), rowColor, TextAlignment.CENTER));
            salesTable.addCell(createLaTeXStyleDataCell(order.getTimeStamp().toString(), rowColor, TextAlignment.CENTER));
            salesTable.addCell(createLaTeXStyleDataCell(order.getCustomer().getPhoneNumber(), rowColor, TextAlignment.LEFT));
            salesTable.addCell(createLaTeXStyleDataCell(String.valueOf(order.getOrderItems().size()), rowColor, TextAlignment.CENTER));

            isAlternateRow = !isAlternateRow;
        }
        // Add the table to the document with separator
        document.add(new LineSeparator(new SolidLine(1))); // Subtle separator to match other sections
        document.add(salesTable);
        document.add(new AreaBreak(AreaBreakType.NEXT_PAGE)); // Page break after the table
    }


    private void addInventorySection(Document document)
    {
        document.add(new Paragraph("PRODUCT AND INVENTORY DETAILS")
                .setFontSize(24)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);
        List<ImportTransaction> transactions = importTransactionRepository.findTransactionsInDateRange(startDate, endDate);

        Table inventoryTable = new Table(new float[]{3, 2, 2.5f, 1.5f, 3, 1.5f})
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f));

        // Add a single unified header row with a black background
        Cell headerCell = new Cell(1, 6) // Spans all 6 columns
                .setBackgroundColor(new DeviceRgb(10, 136, 219))
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
        //inventoryTable.addHeaderCell(createLaTeXStyleColumnHeader("Supplier"));
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
            //inventoryTable.addCell(createLaTeXStyleDataCell(transaction.getSupplierEnum().getName(), rowColor, TextAlignment.LEFT));
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


    private Cell createLaTeXStyleColumnHeader(String headerText)
    {
        return new Cell()
                .add(new Paragraph(headerText)
                        .setFontSize(10)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE))
                .setBackgroundColor(new DeviceRgb(10, 136, 219)) // Dark blue header background for consistency
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(8)
                .setBorder(Border.NO_BORDER);
    }


    private Cell createLaTeXStyleDataCell(String content, Color backgroundColor, TextAlignment alignment)
    {
        return new Cell()
                .add(new Paragraph(content)
                        .setFontSize(9))
                .setBackgroundColor(backgroundColor)
                .setTextAlignment(alignment)
                .setPadding(8)
                .setBorder(Border.NO_BORDER);
    }


    private void addFinancialSection(Document document)
    {
        document.add(new Paragraph("FINANCIAL SUMMARY")
                .setFontSize(24)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setMarginBottom(20));

        LocalDate endDate = LocalDate.now();
        LocalDate startDate = endDate.minusDays(7);

        List<Order> orders = orderRepository.findOrdersByStatus(OrderStatusEnum.SUCCESSFUL);
        List<ImportTransaction> importTransactions = importTransactionRepository.findTransactionsInDateRange(startDate, endDate);

        double totalRevenue = orders.stream()
                .mapToDouble(order -> order.getPayment().getAmount())
                .sum();

        double totalImportCosts = importTransactions.stream()
                .mapToDouble(ImportTransaction::getTotalCost)
                .sum();

        double averageRevenuePerOrder = orders.isEmpty() ? 0 : totalRevenue / orders.size();
        double profitMargin = (totalRevenue - totalImportCosts) / totalRevenue * 100;

        // Define the table with column proportions
        Table financialTable = new Table(new float[]{3, 2.5f, 2.5f, 2})
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f));

        // Add a single unified header row with a black background
        Cell headerCell = new Cell(1, 4) // Spans all 4 columns
                .setBackgroundColor(new DeviceRgb(10, 136, 219))
                .add(new Paragraph("FINANCIAL DETAILS")
                        .setFontSize(12)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE)
                        .setTextAlignment(TextAlignment.CENTER))
                .setPadding(10)
                .setBorder(Border.NO_BORDER);

        financialTable.addHeaderCell(headerCell);

        // Add headers with bold, gray text and black background
        financialTable.addHeaderCell(createLaTeXStyleColumnHeader("Category"));
        financialTable.addHeaderCell(createLaTeXStyleColumnHeader("Value"));
        financialTable.addHeaderCell(createLaTeXStyleColumnHeader("Additional Info"));
        financialTable.addHeaderCell(createLaTeXStyleColumnHeader("Percentage"));

        // Add rows dynamically with alternating row colors and professional padding
        boolean isAlternateRow = false;

        // Total Revenue Row
        financialTable.addCell(createLaTeXStyleDataCell("Total Revenue", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell(String.format("$%.2f", totalRevenue), getRowColor(isAlternateRow), TextAlignment.RIGHT));
        financialTable.addCell(createLaTeXStyleDataCell("All completed orders", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell("", getRowColor(isAlternateRow), TextAlignment.CENTER));
        isAlternateRow = !isAlternateRow;

        // Total Import Costs Row
        financialTable.addCell(createLaTeXStyleDataCell("Total Import Costs", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell(String.format("$%.2f", totalImportCosts), getRowColor(isAlternateRow), TextAlignment.RIGHT));
        financialTable.addCell(createLaTeXStyleDataCell("Last 7 days", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell("", getRowColor(isAlternateRow), TextAlignment.CENTER));
        isAlternateRow = !isAlternateRow;

        // Profit Row
        financialTable.addCell(createLaTeXStyleDataCell("Profit", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell(String.format("$%.2f", totalRevenue - totalImportCosts), getRowColor(isAlternateRow), TextAlignment.RIGHT));
        financialTable.addCell(createLaTeXStyleDataCell("Revenue - Costs", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell("", getRowColor(isAlternateRow), TextAlignment.CENTER));
        isAlternateRow = !isAlternateRow;

        // Average Revenue per Order Row
        financialTable.addCell(createLaTeXStyleDataCell("Avg Revenue per Order", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell(String.format("$%.2f", averageRevenuePerOrder), getRowColor(isAlternateRow), TextAlignment.RIGHT));
        financialTable.addCell(createLaTeXStyleDataCell("Across all orders", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell("", getRowColor(isAlternateRow), TextAlignment.CENTER));
        isAlternateRow = !isAlternateRow;

        // Profit Margin Row
        financialTable.addCell(createLaTeXStyleDataCell("Profit Margin", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell("", getRowColor(isAlternateRow), TextAlignment.RIGHT));
        financialTable.addCell(createLaTeXStyleDataCell("Percentage", getRowColor(isAlternateRow), TextAlignment.LEFT));
        financialTable.addCell(createLaTeXStyleDataCell(String.format("%.2f%%", profitMargin), getRowColor(isAlternateRow), TextAlignment.CENTER));

        // Add subtle divider between sections
        document.add(new LineSeparator(new SolidLine(1)));
        document.add(financialTable);
        //document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
    }


    // Helper to get alternating row colors
    private Color getRowColor(boolean isAlternateRow)
    {
        return isAlternateRow ? new DeviceRgb(240, 240, 240) : ColorConstants.WHITE;
    }


    private void addHandwritingNoteSection(Document document)
    {
        // Add Title for Notes Section
        document.add(new Paragraph("Notes Section")
                .setFontSize(14)
                .setBold()
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginBottom(10));

        // Add Notes with handwriting line
        document.add(new Paragraph("Notes: ________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginBottom(20));

        // Add Approval Section
        Table approvalTable = new Table(new float[]{3, 1, 1})
                .setWidth(UnitValue.createPercentValue(100))
                .setBorder(new SolidBorder(ColorConstants.GRAY, 0.5f));

        // Header for approval section
        approvalTable.addHeaderCell(new Cell(1, 3)
                .setBackgroundColor(new DeviceRgb(10, 136, 219))
                .add(new Paragraph("Approval Section")
                        .setFontSize(12)
                        .setBold()
                        .setFontColor(ColorConstants.WHITE)
                        .setTextAlignment(TextAlignment.LEFT)) // Align header to the left
                .setPadding(5)
                .setBorder(Border.NO_BORDER));

        // Add Approval Fields
        approvalTable.addCell(new Paragraph("Approved: ___________________________________")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT) // Label aligned to the left
                .setPaddingLeft(10));

        // Yes/No options aligned to the right side
        approvalTable.addCell(new Paragraph("Yes")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingRight(10));

        approvalTable.addCell(new Paragraph("No")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.RIGHT)
                .setPaddingRight(10));

        document.add(approvalTable);

        // Add Reviewed By Section
        document.add(new Paragraph("Reviewed By: _______________________________________________")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginTop(20));

        // Add Authorized Signature Section
        document.add(new Paragraph("Authorized Signature: _______________________________________________")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginTop(20));

        // Add Signature Date Section
        document.add(new Paragraph("Date: ______________________")
                .setFontSize(12)
                .setTextAlignment(TextAlignment.LEFT)
                .setMarginBottom(20));
    }


    private Table createTableTemplate(String[] headers)
    {
        Table table = new Table(headers.length)
                .setWidth(UnitValue.createPercentValue(90))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBorder(new SolidBorder(1));

        for (String header : headers) {
            table.addHeaderCell(createHeaderCell(header));
        }

        return table;
    }


    private Cell createHeaderCell(String content)
    {
        return new Cell()
                .add(new Paragraph(content).setBold().setFontColor(WebColors.getRGBColor("#2C3E50")))
                .setBackgroundColor(WebColors.getRGBColor("#ECF0F1"))
                .setTextAlignment(TextAlignment.CENTER)
                .setPadding(8);
    }
}