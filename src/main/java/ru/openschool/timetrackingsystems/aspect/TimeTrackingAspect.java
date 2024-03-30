package ru.openschool.timetrackingsystems.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.openschool.timetrackingsystems.annotation.TrackAsyncTime;
import ru.openschool.timetrackingsystems.annotation.TrackTime;

@Component
@Aspect
public class TimeTrackingAspect {

    @Pointcut("@annotation(trackTime)")
    public void synchTrackingPointcut(TrackTime trackTime) {
    }

    @Pointcut("@annotation(trackAsyncTime)")
    public void asynchTrackingPointcut(TrackAsyncTime trackAsyncTime) {
    }
}
