package com.vitali.runstats.service.api;

import com.vitali.runstats.dto.RunningReportDto;

import java.util.List;

public interface RunningReportService {

    List<RunningReportDto> getByUserId(Long userId);

}