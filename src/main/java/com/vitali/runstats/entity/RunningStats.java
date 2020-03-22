package com.vitali.runstats.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "runstats")
public class RunningStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private LocalDate date;

    private Long distance;

    private Double time;

    public RunningStats() {
    }

    public RunningStats(Long id, User user, LocalDate date, Long distance, Double time) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
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
