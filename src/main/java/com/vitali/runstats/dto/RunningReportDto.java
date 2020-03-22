package com.vitali.runstats.dto;

import java.time.LocalDate;

public class RunningReportDto {

    private Long numberOfWeek;

    private LocalDate weekStart;

    private LocalDate weekEnd;

    private Double averageSpeedInMeterPerSecond;

    private Double averageTimeInSeconds;

    private Long totalDistanceInMeters;

    public RunningReportDto() {
    }

    public Long getNumberOfWeek() {
        return numberOfWeek;
    }

    public RunningReportDto setNumberOfWeek(Long numberOfWeek) {
        this.numberOfWeek = numberOfWeek;
        return this;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public RunningReportDto setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
        return this;
    }

    public LocalDate getWeekEnd() {
        return weekEnd;
    }

    public RunningReportDto setWeekEnd(LocalDate weekEnd) {
        this.weekEnd = weekEnd;
        return this;
    }

    public Double getAverageSpeedInMeterPerSecond() {
        return averageSpeedInMeterPerSecond;
    }

    public RunningReportDto setAverageSpeedInMeterPerSecond(Double averageSpeedInMeterPerSecond) {
        this.averageSpeedInMeterPerSecond = averageSpeedInMeterPerSecond;
        return this;
    }

    public Double getAverageTimeInSeconds() {
        return averageTimeInSeconds;
    }

    public RunningReportDto setAverageTimeInSeconds(Double averageTimeInSeconds) {
        this.averageTimeInSeconds = averageTimeInSeconds;
        return this;
    }

    public Long getTotalDistanceInMeters() {
        return totalDistanceInMeters;
    }

    public RunningReportDto setTotalDistanceInMeters(Long totalDistanceInMeters) {
        this.totalDistanceInMeters = totalDistanceInMeters;
        return this;
    }
}
