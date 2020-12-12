package com.smartmeetings.server;

import com.smartmeetings.server.model.Lesson;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Scope("prototype")
public class TimetableScanner {
    // Todo do not open connection each time if you need it.
    public List<Lesson> scanTimetable(int groupNumber) {
        // Todo write your parsing code here

        // Tests only
        /*Lesson lesson1 = Lesson.builder()
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setStartTime(LocalTime.of(10, 50))
                .setEndTime(LocalTime.of(12, 40))
                .setTitle("Test lesson 12")
                .build();

        Lesson lesson2 = Lesson.builder()
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setStartTime(LocalTime.of(12, 40))
                .setEndTime(LocalTime.of(14, 30))
                .setTitle("Test lesson 13")
                .build();

        Lesson lesson3 = Lesson.builder()
                .setDayOfWeek(DayOfWeek.MONDAY)
                .setStartTime(LocalTime.of(14, 30))
                .setEndTime(LocalTime.of(16, 20))
                .buildEmpty();

        return List.of(lesson1, lesson2, lesson3);*/

        return List.of();
    }
}