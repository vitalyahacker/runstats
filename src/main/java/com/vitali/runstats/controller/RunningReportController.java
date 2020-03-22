package com.vitali.runstats.controller;

import com.vitali.runstats.dto.RunningReportDto;
import com.vitali.runstats.service.report.RunningReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/report")
public class RunningReportController {

    private final RunningReportService runningReportService;

    public RunningReportController(RunningReportService runningReportService) {
        this.runningReportService = runningReportService;
    }

    @GetMapping("/user/{id}")
    public List<RunningReportDto> getReportsByUserId(@PathVariable("id") Long userId) {
        return runningReportService.getByUserId(userId);
    }

}
