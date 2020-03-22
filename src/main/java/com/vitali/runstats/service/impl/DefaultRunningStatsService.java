package com.vitali.runstats.service.impl;

import com.vitali.runstats.dto.RunningStatsDto;
import com.vitali.runstats.entity.RunningStats;
import com.vitali.runstats.repo.RunningStatsRepo;
import com.vitali.runstats.service.api.RunningStatsService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DefaultRunningStatsService implements RunningStatsService {

    private final RunningStatsRepo runningStatsRepo;

    private final ModelMapper modelMapper;

    public DefaultRunningStatsService(RunningStatsRepo runningStatsRepo, ModelMapper modelMapper) {
        this.runningStatsRepo = runningStatsRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<RunningStatsDto> getByUserId(Long userId) {
        return runningStatsRepo.findByUserId(userId)
                .stream()
                .map(e -> modelMapper.map(e, RunningStatsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RunningStatsDto> get(Long runningStatsId) {
        Optional<RunningStats> runStatsOptional = runningStatsRepo.findById(runningStatsId);
        return runStatsOptional
                .map(runningStats -> modelMapper.map(runningStats, RunningStatsDto.class));
    }

    @Override
    public RunningStatsDto save(RunningStatsDto runningStatsDto) {
        return modelMapper.map(
                runningStatsRepo.save(modelMapper.map(runningStatsDto, RunningStats.class)),
                RunningStatsDto.class
        );
    }

    @Override
    public void delete(Long runningStatsId) {
        runningStatsRepo.deleteById(runningStatsId);
    }

}
