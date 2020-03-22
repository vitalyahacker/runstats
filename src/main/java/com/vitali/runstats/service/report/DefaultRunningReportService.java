package com.vitali.runstats.service.report;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.entity.RunningStats;
import com.vitali.runstats.repo.RunningStatsRepo;
import com.vitali.runstats.service.report.aggregator.RunningReportAggregator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultRunningReportService implements RunningReportService {

    private final RunningStatsRepo runningStatsRepo;
    private final RunningReportAggregator reportAggregator;

    public DefaultRunningReportService(
            RunningStatsRepo runningStatsRepo,
            RunningReportAggregator reportAggregator
    ) {
        this.runningStatsRepo = runningStatsRepo;
        this.reportAggregator = reportAggregator;
    }

    @Override
    public List<RunningReportDto> getByUserId(Long userId) {
        LocalDate startDate = runningStatsRepo.findEarliestRunningStats(userId).getDate();
        LocalDate lastExistingDate = runningStatsRepo.findLatestRunningStats(userId).getDate();
        List<RunningReportDto> resultList = new ArrayList<>();

        LocalDate endDate = startDate.plusWeeks(1);
        long numberOfWeek = 0;

        while (!startDate.isAfter(lastExistingDate)) {

            List<RunningStats> statsForPeriod = runningStatsRepo.findByUserIdAndDateBetween(userId, startDate, endDate);
            RunningReportDto reportForWeek = reportAggregator.aggregateReportForWeek(statsForPeriod, ++numberOfWeek, startDate, endDate);

            startDate = endDate;
            endDate = startDate.plusWeeks(1);
            resultList.add(reportForWeek);
        }

        return resultList;
    }

}
