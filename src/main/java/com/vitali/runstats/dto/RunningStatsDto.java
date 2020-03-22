package com.vitali.runstats.dto;

import java.time.LocalDate;

public class RunningStatsDto {

    private Long id;

    private UserDto user;

    private LocalDate date;

    private Long distance;

    private Double time;

    public RunningStatsDto() {
    }

    public RunningStatsDto(Long id, UserDto user, LocalDate date, Long distance, Double time) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.distance = distance;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
