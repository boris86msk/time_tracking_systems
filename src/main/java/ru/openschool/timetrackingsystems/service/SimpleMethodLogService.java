package ru.openschool.timetrackingsystems.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.openschool.timetrackingsystems.model.MethodLog;
import ru.openschool.timetrackingsystems.repository.MethodLogRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleMethodLogService implements MethodLogService {
    private final MethodLogRepository methodLogRepository;

    @Async
    @Override
    public void saveTracking(long nano, long millis, String name, boolean sync) {
        MethodLog methodLog = new MethodLog();
        methodLog.setName(name);
        methodLog.setExecuted(LocalDateTime.now());
        if(millis > 5) {
            methodLog.setMilliSecond(millis);
        } else if(millis < 1) {
            methodLog.setNanoSecond(nano);
        } else {
            methodLog.setMilliSecond(millis);
            methodLog.setNanoSecond(nano);
        }
        methodLog.setAsynchronous(sync);

        methodLogRepository.save(methodLog);
    }
}
