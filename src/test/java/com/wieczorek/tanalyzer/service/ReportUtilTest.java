package com.wieczorek.tanalyzer.service;

import com.wieczorek.tanalyzer.model.Report;
import java.io.File;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;

public class ReportUtilTest {

    @Test
    public void shouldCalculateReport() {
        //given
        File file = new File(getClass().getClassLoader().getResource("data.csv").getFile());
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "20/08/2018 13:00:00";
        String merchant = "Kwik-E-Mart";

        //when
        Report report = ReportService.analyze(file, fromDate, toDate, merchant);

        //then
        Assert.assertEquals(1, report.getNumberOfTransactions());
        Assert.assertEquals(new BigDecimal("59.99"), report.getAverageAmount());
    }

    @Test(expected = java.time.format.DateTimeParseException.class)
    public void shouldFailCalculateReportWhenBrokenFromDate() {
        //given
        File file = new File(getClass().getClassLoader().getResource("data.csv").getFile());
        String fromDate = "20/08/2018 12:00:00aaa";
        String toDate = "20/08/2018 13:00:00";
        String merchant = "Kwik-E-Mart";

        //when
        ReportService.analyze(file, fromDate, toDate, merchant);

        //then exception
    }

    @Test(expected = java.time.format.DateTimeParseException.class)
    public void shouldFailCalculateReportWhenBrokenToDate() {
        //given
        File file = new File(getClass().getClassLoader().getResource("data.csv").getFile());
        String fromDate = "20/08/2018 12:00:00";
        String toDate = "";
        String merchant = "Kwik-E-Mart";

        //when
        ReportService.analyze(file, fromDate, toDate, merchant);

        //then exception
    }
}
