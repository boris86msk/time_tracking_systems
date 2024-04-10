package ru.openschool.timetrackingsystems.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import ru.openschool.timetrackingsystems.service.MethodLogService;

@Component
@Aspect
@RequiredArgsConstructor
@Slf4j
public class TimeTrackingAspect {
    private final MethodLogService methodLogService;

    @Pointcut("execution(@ru.openschool.timetrackingsystems.annotation.TrackTime * *(..))")
    public void synchronTrackingPointcut() {
    }

    @Pointcut("execution(@ru.openschool.timetrackingsystems.annotation.TrackAsyncTime * *(..))")
    public void asynchronTrackingPointcut() {
    }

    @Around("synchronTrackingPointcut()")
    public Object synchronMethod(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = timeTrack(joinPoint, false);
        } catch (Throwable e) {
            log.error("RuntimeException: " + e);
        }
        return result;
    }

    @Async
    @Around("asynchronTrackingPointcut()")
    public void asynchronMethod(ProceedingJoinPoint joinPoint) {
        try {
            timeTrack(joinPoint, true);
        } catch (Throwable e) {
            log.error("RuntimeException: " + e);
        }
    }

    private Object timeTrack(ProceedingJoinPoint joinPoint, boolean async) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startMillis = System.currentTimeMillis();
        long startNano = System.nanoTime();

        Object result = joinPoint.proceed();

        long endNano = System.nanoTime();
        long endMillis = System.currentTimeMillis();

        methodLogService.saveTracking(endNano - startNano, endMillis - startMillis,
                methodName, async);
        return result;
    }
}
