package ru.openschool.timetrackingsystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.openschool.timetrackingsystems.model.MethodLog;

import java.util.List;

@Repository
public interface MethodLogRepository extends JpaRepository<MethodLog, Long> {
    List<MethodLog> findMethodLogByMilliSecondLessThan(long milliSecond);
    List<MethodLog> findMethodLogByMilliSecondGreaterThan(long milliSecond);
    List<MethodLog> findMethodLogByAsynchronousTrue();
    @Query("SELECT AVG (m.milliSecond) FROM MethodLog m")
    long avgTimeExecution();
    List<MethodLog> findMethodLogByNameContains(String name);
}
