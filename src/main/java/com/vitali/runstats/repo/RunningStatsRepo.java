package com.vitali.runstats.repo;

import com.vitali.runstats.entity.RunningStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface RunningStatsRepo extends CrudRepository<RunningStats, Long> {

    List<RunningStats> findByUserId(Long userId);

    List<RunningStats> findByUserIdAndDateBetween(Long userId, LocalDate start, LocalDate end);

    @Query("select r from RunningStats r where r.user.id = :userId and r.date = (" +
            "select min(r.date) from RunningStats r where r.user.id = :userId)")
    RunningStats findEarliestRunningStats(Long userId);

    @Query("select r from RunningStats r where r.user.id = :userId and r.date = (" +
            "select max(r.date) from RunningStats r where r.user.id = :userId)")
    RunningStats findLatestRunningStats(Long userId);

}
