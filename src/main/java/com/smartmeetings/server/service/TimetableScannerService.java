package com.smartmeetings.server.service;

import com.smartmeetings.server.TimetableScanExecutor;
import com.smartmeetings.server.data.GroupsRepo;
import com.smartmeetings.server.data.TimetableRepo;
import com.smartmeetings.server.model.Lesson;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableScannerService {

    private final ThreadPoolTaskScheduler taskScheduler;
    private final TimetableRepo timetableRepo;
    private final GroupsRepo groupsRepo;
    public TimetableScannerService(ThreadPoolTaskScheduler taskScheduler, TimetableRepo timetableRepo, GroupsRepo groupsRepo) {
        this.taskScheduler = taskScheduler;
        this.timetableRepo = timetableRepo;
        this.groupsRepo = groupsRepo;
    }

    public void launchScanning() {
        taskScheduler.scheduleWithFixedDelay(new TimetableScanExecutor(this), 5000);
    }

    public void addToTimetable(int groupId, List<Lesson> lessons) {
        timetableRepo.insertWithQuery(groupId, lessons);
    }

    public List<Integer> getAvailableGroups() {
        return groupsRepo.getAvailableGroupNumbers();
    }
}
