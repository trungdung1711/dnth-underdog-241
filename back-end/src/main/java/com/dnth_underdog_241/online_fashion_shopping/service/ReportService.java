package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.config.report.PdfReportGenerator;
import com.dnth_underdog_241.online_fashion_shopping.model.Report;
import com.dnth_underdog_241.online_fashion_shopping.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Transactional
public class ReportService
{
    private final PdfReportGenerator pdfReportGenerator;
    private final ReportRepository reportRepository;


    public void createReport()
    {
        Report report = new Report();
        report.setDate(LocalDate.now());

        String fileName = "Consolidated_Report_" + report.getDate() + ".pdf";
        report.setUrl(pdfReportGenerator.generateWeeklyReportTemplate(fileName));

        if (reportRepository.existsByDate(report.getDate()))
        {
            return;
        }

        reportRepository.save(report);
    }
}