package ru.openschool.timetrackingsystems.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.openschool.timetrackingsystems.model.MethodLog;

@Repository
public interface MethodLogRepository extends JpaRepository<MethodLog, Long> {
}
