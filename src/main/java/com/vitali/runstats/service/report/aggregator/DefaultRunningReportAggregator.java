package com.vitali.runstats.service.report.aggregator;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.entity.RunningStats;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DefaultRunningReportAggregator implements RunningReportAggregator {

    @Override
    public RunningReportDto aggregateReportForWeek(
            List<RunningStats> statsForPeriod,
            long numberOfWeek,
            LocalDate startDate,
            LocalDate endDate
    ) {
        return new RunningReportDto()
                .setNumberOfWeek(numberOfWeek)
                .setWeekStart(startDate)
                .setWeekEnd(endDate)
                .setTotalDistanceInMeters(
                        statsForPeriod.stream()
                                .mapToLong(RunningStats::getDistance)
                                .sum()
                )
                .setAverageSpeedInMeterPerSecond(
                        statsForPeriod.stream()
                                .mapToDouble(stats -> stats.getDistance() / stats.getTime())
                                .sum()
                )
                .setAverageTimeInSeconds(
                        statsForPeriod.stream()
                                .mapToDouble(RunningStats::getTime)
                                .average()
                                .orElse(0.0)
                );
    }
}
