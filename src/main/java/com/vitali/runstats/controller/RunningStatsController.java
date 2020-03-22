package com.vitali.runstats.controller;

import com.vitali.runstats.controller.exception.RunstatsException;
import com.vitali.runstats.dto.RunningStatsDto;
import com.vitali.runstats.service.api.RunningStatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/runstats")
public class RunningStatsController {

    private final RunningStatsService runningStatsService;

    public RunningStatsController(RunningStatsService runningStatsService) {
        this.runningStatsService = runningStatsService;
    }

    @GetMapping
    public List<RunningStatsDto> getByUserId(@RequestParam Long userId) {
        return runningStatsService.getByUserId(userId);
    }

    @GetMapping("/{id}")
    public RunningStatsDto getById(@PathVariable("id") Long id) {
        return runningStatsService.get(id).orElseThrow(RunstatsException::new);
    }

    @PostMapping
    public RunningStatsDto save(@RequestBody RunningStatsDto runningStats){
        return runningStatsService.save(runningStats);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        runningStatsService.delete(id);
    }

    @PutMapping("/{id}")
    public RunningStatsDto update(@PathVariable("id") Long id, RunningStatsDto runningStats){
        return runningStatsService.save(runningStats);
    }
}
