package com.vitali.runstats.dto;

import java.time.LocalDate;

public class RunningReportDto {

    private Long numberOfWeek;

    private LocalDate weekStart;

    private LocalDate weekEnd;

    private Double averageSpeedInMeterPerSecond;

    private Double averageTimeInSeconds;

    private Long totalDistanceInMeters;

    public RunningReportDto(
            Long numberOfWeek,
            LocalDate weekStart,
            LocalDate weekEnd,
            Double averageSpeedInMeterPerSecond,
            Double averageTimeInSeconds,
            Long totalDistanceInMeters
    ) {
        this.numberOfWeek = numberOfWeek;
        this.weekStart = weekStart;
        this.weekEnd = weekEnd;
        this.averageSpeedInMeterPerSecond = averageSpeedInMeterPerSecond;
        this.averageTimeInSeconds = averageTimeInSeconds;
        this.totalDistanceInMeters = totalDistanceInMeters;
    }

    public RunningReportDto() {
    }

    public Long getNumberOfWeek() {
        return numberOfWeek;
    }

    public void setNumberOfWeek(Long numberOfWeek) {
        this.numberOfWeek = numberOfWeek;
    }

    public LocalDate getWeekStart() {
        return weekStart;
    }

    public void setWeekStart(LocalDate weekStart) {
        this.weekStart = weekStart;
    }

    public LocalDate getWeekEnd() {
        return weekEnd;
    }

    public void setWeekEnd(LocalDate weekEnd) {
        this.weekEnd = weekEnd;
    }

    public Double getAverageSpeedInMeterPerSecond() {
        return averageSpeedInMeterPerSecond;
    }

    public void setAverageSpeedInMeterPerSecond(Double averageSpeedInMeterPerSecond) {
        this.averageSpeedInMeterPerSecond = averageSpeedInMeterPerSecond;
    }

    public Double getAverageTimeInSeconds() {
        return averageTimeInSeconds;
    }

    public void setAverageTimeInSeconds(Double averageTimeInSeconds) {
        this.averageTimeInSeconds = averageTimeInSeconds;
    }

    public Long getTotalDistanceInMeters() {
        return totalDistanceInMeters;
    }

    public void setTotalDistanceInMeters(Long totalDistanceInMeters) {
        this.totalDistanceInMeters = totalDistanceInMeters;
    }
}
