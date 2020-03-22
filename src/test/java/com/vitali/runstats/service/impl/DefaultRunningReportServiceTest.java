package com.vitali.runstats.service.impl;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.entity.RunningStats;
import com.vitali.runstats.entity.User;
import com.vitali.runstats.repo.RunningStatsRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(value = SpringJUnit4ClassRunner.class)
public class DefaultRunningReportServiceTest {

    private static final Long USER_ID = 123L;
    private static final User user = new User();
    private static final LocalDate FIRST_DATE = now().minusDays(6);
    private RunningStats firstRunningStats;
    private RunningStats secondRunningStats;
    private RunningStats thirdRunningStats;
    private RunningStats fourthRunningStats;

    @Mock
    private RunningStatsRepo runningStatsRepo;

    @InjectMocks
    private DefaultRunningReportService runningReportService;

    @Before
    public void init() {
        user.setId(USER_ID);

        firstRunningStats = new RunningStats(1L, user, FIRST_DATE, 100L, 9.8);
        secondRunningStats = new RunningStats(2L, user, now().minusDays(1), 500L, 57.2);
        thirdRunningStats = new RunningStats(3L, user, now().plusDays(2), 30L, 11.3);
        fourthRunningStats = new RunningStats(4L, user, now().plusDays(6), 240L, 29.1);

        when(runningStatsRepo.findEarliestRunningStats(USER_ID)).thenReturn(firstRunningStats);
        when(runningStatsRepo.findLatestRunningStats(USER_ID)).thenReturn(fourthRunningStats);
        when(runningStatsRepo.findByUserIdAndDateBetween(USER_ID, FIRST_DATE, FIRST_DATE.plusWeeks(1)))
                .thenReturn(Arrays.asList(firstRunningStats, secondRunningStats));
        when(runningStatsRepo.findByUserIdAndDateBetween(USER_ID, FIRST_DATE.plusWeeks(1), FIRST_DATE.plusWeeks(2)))
                .thenReturn(Arrays.asList(thirdRunningStats, fourthRunningStats));
    }

    @Test
    public void shouldBuildReportWithCorrectData() {
        List<RunningReportDto> reports = runningReportService.getByUserId(USER_ID);
        verify(runningStatsRepo, times(1)).findEarliestRunningStats(USER_ID);
        verify(runningStatsRepo, times(1)).findLatestRunningStats(USER_ID);
        verify(runningStatsRepo, times(1)).findByUserIdAndDateBetween(USER_ID,
                FIRST_DATE, FIRST_DATE.plusWeeks(1));
        verify(runningStatsRepo, times(1)).findByUserIdAndDateBetween(USER_ID,
                FIRST_DATE.plusWeeks(1), FIRST_DATE.plusWeeks(2));

        RunningReportDto firstWeekReport = reports.get(0);
        assertEquals(firstWeekReport.getNumberOfWeek().longValue(), 1L);
        assertEquals(firstWeekReport.getWeekStart(), FIRST_DATE);
        assertEquals(firstWeekReport.getWeekEnd(), FIRST_DATE.plusWeeks(1));
        assertEquals(firstWeekReport.getTotalDistanceInMeters().longValue(),
                firstRunningStats.getDistance() + secondRunningStats.getDistance().longValue());
        assertEquals(firstWeekReport.getAverageSpeedInMeterPerSecond().doubleValue(),
                calculateAverageSpeed(firstRunningStats, secondRunningStats), 0.1);
        assertEquals(firstWeekReport.getAverageTimeInSeconds().doubleValue(),
                (firstRunningStats.getTime() + secondRunningStats.getTime())/2, 0.1);

        RunningReportDto secondWeekReport = reports.get(1);
        assertEquals(secondWeekReport.getNumberOfWeek().longValue(), 2L);
        assertEquals(secondWeekReport.getWeekStart(), FIRST_DATE.plusWeeks(1));
        assertEquals(secondWeekReport.getWeekEnd(), FIRST_DATE.plusWeeks(2));
        assertEquals(secondWeekReport.getTotalDistanceInMeters().longValue(),
                thirdRunningStats.getDistance() + fourthRunningStats.getDistance().longValue());
        assertEquals(secondWeekReport.getAverageSpeedInMeterPerSecond().doubleValue(),
                calculateAverageSpeed(thirdRunningStats, fourthRunningStats), 0.1);
        assertEquals(secondWeekReport.getAverageTimeInSeconds().doubleValue(),
                (thirdRunningStats.getTime() + fourthRunningStats.getTime()) / 2, 0.1);
    }

    private double calculateAverageSpeed(RunningStats rs1, RunningStats rs2) {
        return (rs1.getDistance() + rs2.getDistance()) / (rs1.getTime() + rs2.getTime());
    }
}
