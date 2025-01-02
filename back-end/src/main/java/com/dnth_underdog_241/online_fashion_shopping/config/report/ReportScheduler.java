package com.dnth_underdog_241.online_fashion_shopping.config.report;

import com.dnth_underdog_241.online_fashion_shopping.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportScheduler
{
    private final ReportService reportService;


    //@Scheduled(cron = "0 0 0 * * SUN")
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void generateWeeklyReport()
    {
        log.info("Generating weekly report");
        reportService.createReport();
    }
}