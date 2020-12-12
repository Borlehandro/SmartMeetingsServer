package com.smartmeetings.server.data;

import com.smartmeetings.server.model.Lesson;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class TimetableRepo {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertWithQuery(int groupNumber, List<Lesson> lessons) {

        lessons.forEach(lesson -> {
                    if (!lesson.isEmpty()) {
                        entityManager.createNativeQuery("INSERT INTO groups_timetable" +
                                "(group_number, start_time, end_time, day_of_week, title)" +
                                "VALUES (?, ?, ?, CAST (? AS dayofweek), ?)" +
                                "ON CONFLICT(group_number, start_time, end_time, day_of_week)" +
                                "DO UPDATE SET title = $5")
                                .setParameter(1, groupNumber)
                                .setParameter(2, lesson.getStartTime())
                                .setParameter(3, lesson.getEndTime())
                                .setParameter(4, lesson.getDayOfWeek().toString().toLowerCase())
                                .setParameter(5, lesson.getTitle())
                                .executeUpdate();
                    } else {
                        entityManager.createNativeQuery("DELETE FROM groups_timetable " +
                                "WHERE (group_number = ? and start_time = ? and end_time = ? and day_of_week = CASt(? AS dayofweek))")
                        .setParameter(1, groupNumber)
                        .setParameter(2, lesson.getStartTime())
                        .setParameter(3, lesson.getEndTime())
                        .setParameter(4, lesson.getDayOfWeek().toString().toLowerCase())
                        .executeUpdate();
                    }
                }
        );
    }
}