package com.vitali.runstats.service.report;

import com.vitali.runstats.dto.RunningReportDto;

import java.util.List;

public interface RunningReportService {

    List<RunningReportDto> getByUserId(Long userId);

}