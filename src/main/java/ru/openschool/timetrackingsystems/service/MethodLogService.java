package ru.openschool.timetrackingsystems.service;

public interface MethodLogService {
    void saveTracking (long nano, long millis, String name, boolean sinch);
}
