package com.vitali.runstats.service.report.aggregator;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.entity.RunningStats;

import java.time.LocalDate;
import java.util.List;

public interface RunningReportAggregator {
    RunningReportDto aggregateReportForWeek(List<RunningStats> statsForPeriod, long l, LocalDate startDate, LocalDate endDate);
}
