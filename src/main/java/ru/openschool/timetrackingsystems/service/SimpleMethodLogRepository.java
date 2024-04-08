package ru.openschool.timetrackingsystems.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.openschool.timetrackingsystems.model.MethodLog;
import ru.openschool.timetrackingsystems.repository.MethodLogRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SimpleMethodLogRepository implements MethodLogService {
    private final MethodLogRepository methodLogRepository;

    @Async
    @Override
    public void saveTracking(long nano, long millis, String name, boolean sinch) {
        MethodLog methodLog = new MethodLog();
        methodLog.setName(name);
        methodLog.setExecuted(LocalDateTime.now());
        methodLog.setNanoSecond(nano);
        methodLog.setMilliSecond(millis);
        methodLog.setAsynchronous(sinch);
        methodLogRepository.save(methodLog);
    }
}
