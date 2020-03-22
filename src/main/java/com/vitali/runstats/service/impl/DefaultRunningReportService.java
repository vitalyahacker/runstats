package com.vitali.runstats.service.impl;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.entity.RunningStats;
import com.vitali.runstats.repo.RunningStatsRepo;
import com.vitali.runstats.service.api.RunningReportService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultRunningReportService implements RunningReportService {

    private final RunningStatsRepo runningStatsRepo;

    public DefaultRunningReportService(RunningStatsRepo runningStatsRepo) {
        this.runningStatsRepo = runningStatsRepo;
    }

    @Override
    public List<RunningReportDto> getByUserId(Long userId) {
        LocalDate startDate = runningStatsRepo.findEarliestRunningStats(userId).getDate();
        LocalDate lastPossibleDate = runningStatsRepo.findLatestRunningStats(userId).getDate();
        return buildStatistics(userId, startDate, lastPossibleDate);
    }

    private List<RunningReportDto> buildStatistics(Long userId, LocalDate startDate, LocalDate lastPossibleDate) {
        List<RunningReportDto> resultList = new ArrayList<>();
        LocalDate endDate = startDate.plusWeeks(1);
        long numberOfWeek = 0;

        while (!startDate.isAfter(lastPossibleDate)) {

            List<RunningStats> statsForPeriod = runningStatsRepo.findByUserIdAndDateBetween(userId, startDate, endDate);

            RunningReportDto report = new RunningReportDto(++numberOfWeek, startDate, endDate, 0.0, 0.0, 0L);
            buildReport(statsForPeriod, report);

            startDate = endDate;
            endDate = startDate.plusWeeks(1);
            resultList.add(report);
        }

        return resultList;
    }

    private void buildReport(List<RunningStats> statsForPeriod, RunningReportDto report) {
        if (statsForPeriod.isEmpty()) {
            report.setAverageTimeInSeconds(0.0);
            return;
        }

        double totalTime = 0;
        for (int i = 0; i < statsForPeriod.size(); i++) {
            RunningStats stats = statsForPeriod.get(i);
            report.setTotalDistanceInMeters(report.getTotalDistanceInMeters() + stats.getDistance());
            totalTime += stats.getTime();
            if (totalTime != 0) {
                report.setAverageSpeedInMeterPerSecond(report.getTotalDistanceInMeters() / totalTime);
            } else {
                report.setAverageSpeedInMeterPerSecond(0.0);
            }
        }
        report.setAverageTimeInSeconds(totalTime / statsForPeriod.size());

    }

}
