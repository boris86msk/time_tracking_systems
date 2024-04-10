package ru.openschool.timetrackingsystems.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.openschool.timetrackingsystems.model.MethodLog;
import ru.openschool.timetrackingsystems.repository.MethodLogRepository;

import java.util.List;

@RestController
@AllArgsConstructor
public class TimeTrackingController {
    private final MethodLogRepository methodLogRepository;

    @GetMapping("/allTracking")
    public List<MethodLog> getAllTracing() {
        return methodLogRepository.findAll();
    }

    @GetMapping("/fast_methods")
    public List<MethodLog> getMethodsFastExecution() {
        return methodLogRepository.findMethodLogByMilliSecondLessThan(1);
    }

    @GetMapping("/long_methods")
    public List<MethodLog> getMethodsLongExecution() {
        return methodLogRepository.findMethodLogByMilliSecondGreaterThan(1000);
    }

    @GetMapping("/async_methods")
    public List<MethodLog> getMethodsLongByAsync() {
        return methodLogRepository.findMethodLogByAsynchronousTrue();
    }

    @GetMapping("/avg_time")
    public long getAvgTimeExecution() {
        return methodLogRepository.avgTimeExecution();
    }

    @GetMapping("/by_name/{name}")
    public List<MethodLog> getList(@PathVariable String name) {
        return methodLogRepository.findMethodLogByNameContains(name);
    }
}
