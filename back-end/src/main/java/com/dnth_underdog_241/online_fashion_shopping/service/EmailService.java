package com.dnth_underdog_241.online_fashion_shopping.service;

import com.dnth_underdog_241.online_fashion_shopping.model.user.Admin;
import com.dnth_underdog_241.online_fashion_shopping.model.user.Employee;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class EmailService
{
    private final JavaMailSender mailSender;


    public void sendEmailWithAttachment(String toEmail, String attachmentPath)
    {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try
        {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom("ofs.shop@gmail.com");
            helper.setTo(toEmail);
            helper.setSubject("Weekly report for Online Fashion Shopping (OFS)");
            helper.setText("The report is automatically generated, please inspect it carefully. Don't reply. Thank you! Have a good day!");

            if (attachmentPath != null)
            {
                FileSystemResource file = new FileSystemResource(new File(attachmentPath));
                helper.addAttachment("WeeklyReport.pdf", file);
            }

            mailSender.send(mimeMessage);
            log.info("Email sent successfully.");
        } catch (MessagingException e)
        {
            log.error("Error while sending email: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void sendEmailToAllAdminsAndEmployees(List<Admin> admins, List<Employee> employees, String attachmentPath)
    {
        admins
                .forEach(admin -> {sendEmailWithAttachment(admin.getEmail(), attachmentPath);});

        employees
                .forEach(employee -> {sendEmailWithAttachment(employee.getEmail(), attachmentPath);});
    }
}