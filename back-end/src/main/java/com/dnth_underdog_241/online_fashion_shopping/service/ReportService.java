package com.dnth_underdog_241.online_fashion_shopping.service;


import com.dnth_underdog_241.online_fashion_shopping.config.report.PdfReportGenerator;
import com.dnth_underdog_241.online_fashion_shopping.model.Report;
import com.dnth_underdog_241.online_fashion_shopping.repository.AdminRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.EmployeeRepository;
import com.dnth_underdog_241.online_fashion_shopping.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
@Transactional
public class ReportService
{
    @Value("${com.dnth_underdog_241.online_fashion_shopping.upload.reports}")
    private String reportDirectory;


    private final EmailService emailService;


    private final AdminRepository adminRepository;


    private final EmployeeRepository employeeRepository;


    private final PdfReportGenerator pdfReportGenerator;


    private final ReportRepository reportRepository;


    public void createReport()
    {
        Report report = new Report();
        report.setDate(LocalDate.now());

        String fileName = "Consolidated_Report_" + report.getDate() + ".pdf";
        report.setUrl(pdfReportGenerator.generateWeeklyReportTemplate(fileName));
        emailService.sendEmailToAllAdminsAndEmployees(adminRepository.findAll(), employeeRepository.findAll(), reportDirectory + fileName);

        if (reportRepository.existsByDate(report.getDate()))
        {
            return;
        }

        reportRepository.save(report);
    }
}