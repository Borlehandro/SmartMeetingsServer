package com.smartmeetings.server.model;

import java.time.DayOfWeek;
import java.time.LocalTime;

/**
 * Lesson model-class.
 * Use only in timetable parsing and logic, not in database.
 */
public class Lesson {
    private String title;
    private final LocalTime startTime;
    private final LocalTime endTime;
    private final DayOfWeek dayOfWeek;
    private final boolean empty;

    private Lesson(String title, LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        empty = false;
    }

    private Lesson(LocalTime startTime, LocalTime endTime, DayOfWeek dayOfWeek) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
        empty = true;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String title;
        private LocalTime startTime;
        private LocalTime endTime;
        private DayOfWeek dayOfWeek;

        private Builder(){}

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setStartTime(LocalTime startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder setEndTime(LocalTime endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder setDayOfWeek(DayOfWeek dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
            return this;
        }

        public Lesson build() {
            if(title == null || startTime == null || endTime == null || dayOfWeek == null)
                throw new IllegalStateException("Some field was not initialized");
            return new Lesson(title, startTime, endTime, dayOfWeek);
        }

        public Lesson buildEmpty() {
            if(startTime == null || endTime == null || dayOfWeek == null)
                throw new IllegalStateException("Some field was not initialized");
            return new Lesson(startTime, endTime, dayOfWeek);
        }

    }

    public String getTitle() {
        return title;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean isEmpty() {
        return empty;
    }
}