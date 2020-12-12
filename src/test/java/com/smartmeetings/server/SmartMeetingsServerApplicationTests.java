package com.smartmeetings.server;

import com.smartmeetings.server.data.GroupsRepo;
import com.smartmeetings.server.service.TimetableScannerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SmartMeetingsServerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	TimetableScannerService service;
	@Autowired
	TimetableScanExecutor executor;
	@Autowired
	TimetableScanner scanner;

	@Test
	void updateTimetable() {
		executor.run();
		scanner.scanTimetable(1);
	}

//	@Test
//	void testGroupsRepo() {
//		List<Integer> realGroups = List.of(1,2,3);
//		Assertions.assertArrayEquals(realGroups.toArray(new Integer[0]),
//				groupsRepo.getAvailableGroups().toArray(new Integer[0]));
//	}

}