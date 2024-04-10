package ru.openschool.timetrackingsystems.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.openschool.timetrackingsystems.model.MethodLog;
import ru.openschool.timetrackingsystems.repository.MethodLogRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
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

    @ExceptionHandler(value = {Exception.class})
    public void catchDataIntegrityViolationException(Exception e, HttpServletRequest request,
                                                     HttpServletResponse response) throws IOException {
        Map<String, String> details = new HashMap<>();
        details.put("message", e.getMessage());
        details.put("type", String.valueOf(e.getClass()));
        details.put("timestamp", String.valueOf(LocalDateTime.now()));
        details.put("path", request.getRequestURI());
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType("application/json; charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(details));
        log.error(e.getLocalizedMessage());
    }
}
