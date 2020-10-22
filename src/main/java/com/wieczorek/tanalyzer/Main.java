package com.wieczorek.tanalyzer;

import com.wieczorek.tanalyzer.service.ReportService;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        if (args == null || args.length != 4) {
            System.out.println("Application requires 4 parameters: [CSV file path] [merchant name] [start date (dd/MM/yyyy HH:mm:ss)] [end date (dd/MM/yyyy HH:mm:ss)]");
            System.out.println("For example: java -jar /somefile.csv \"20/08/2018 12:00:00\" \"20/08/2018 13:00:00\" Kwik-E-Mart");
            System.exit(1);
        }

        String filePath = args[0];
        String fromDate = args[1];
        String toDate = args[2];
        String merchant = args[3];

        //System.out.println("Chosen parameters:");
        //System.out.println(String.format("==>> filePath: %s", filePath));
        //System.out.println(String.format("==>> fromDate: %s", fromDate));
        //System.out.println(String.format("==>> toDate: %s", toDate));
        //System.out.println(String.format("==>> merchant: %s", merchant));

        ReportService.analyze(new File(filePath), fromDate, toDate, merchant);
    }
}
