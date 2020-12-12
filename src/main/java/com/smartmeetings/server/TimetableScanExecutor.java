package com.smartmeetings.server;

import com.smartmeetings.server.service.TimetableScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimetableScanExecutor implements Runnable {

    private final TimetableScannerService scannerService;

    public TimetableScanExecutor(TimetableScannerService scannerService) {
        this.scannerService = scannerService;
    }

    @Override
    public void run() {
        // Todo scan only ONE new group if you need it
        List<Integer> groupsNumbers = scannerService.getAvailableGroups();
        groupsNumbers.forEach(groupsNumber -> {
                    System.out.println("Try to scanning " + groupsNumber);
                    scannerService.addToTimetable(groupsNumber, new TimetableScanner().scanTimetable(groupsNumber));
                }
        );
    }
}