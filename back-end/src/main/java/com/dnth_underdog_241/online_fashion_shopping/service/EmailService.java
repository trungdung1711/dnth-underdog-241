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

            helper.setFrom("noreply@ofs241.com");
            helper.setTo(toEmail);
            helper.setSubject("Weekly Performance Report - Online Fashion Shopping (OFS)");
            helper.setText(
                "Subject: Weekly Report - Online Fashion Shopping (OFS)\n\n" +
                "Dear Team Member,\n\n" +
                "We hope this message finds you well. Please find attached the Weekly Report for the Online Fashion Shopping (OFS) system, covering key metrics, updates, and insights from the past week.\n\n" +
                "We kindly request you to review the attached report thoroughly. It contains important information that supports operational decision-making and performance tracking across the platform. Should you have any questions or require further clarification, please do not hesitate to contact your department supervisor or the OFS Support Team at support@ofs241.com.\n\n" +
                "Please note that this is an automatically generated email. Replies to this email will not be monitored.\n\n" +
                "Thank you for your attention and continuous dedication to the success of the OFS platform. Your efforts contribute significantly to delivering an exceptional experience for our users and partners.\n\n" +
                "We wish you a productive and successful week ahead.\n\n" +
                "Warm regards,\n\n" +
                "The OFS Support Team\n" +
                "Online Fashion Shopping (OFS)\n" +
                "Website: www.ofs241.com\n" +
                "Email: support@ofs241.com\n"
            );
            

            if (attachmentPath != null)
            {
                FileSystemResource file = new FileSystemResource(new File(attachmentPath));
                helper.addAttachment("weekly-report.pdf", file);
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