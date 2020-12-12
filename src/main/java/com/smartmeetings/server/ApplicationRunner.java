package com.smartmeetings.server;

import com.smartmeetings.server.service.TimetableScannerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private final TimetableScannerService timetableScannerService;

    public ApplicationRunner(TimetableScannerService timetableScannerService) {
        this.timetableScannerService = timetableScannerService;
    }

    @Override
    public void run(String... args) {
        timetableScannerService.launchScanning();
    }
}
