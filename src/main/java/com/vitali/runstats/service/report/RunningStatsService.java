package com.vitali.runstats.service.report;

import com.vitali.runstats.dto.RunningStatsDto;

import java.util.List;
import java.util.Optional;

public interface RunningStatsService {

    List<RunningStatsDto> getByUserId(Long userId);

    Optional<RunningStatsDto> get(Long runningStatsId);

    RunningStatsDto save(RunningStatsDto runningStats);

    void delete(Long runningStatsId);

}