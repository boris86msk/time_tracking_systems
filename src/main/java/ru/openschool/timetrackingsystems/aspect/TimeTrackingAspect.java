package ru.openschool.timetrackingsystems.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.openschool.timetrackingsystems.service.MethodLogService;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@RequiredArgsConstructor
public class TimeTrackingAspect {
    private final MethodLogService methodLogService;

    @Pointcut("@annotation(ru.openschool.timetrackingsystems.annotation.TrackTime)")
    public void synchronTrackingPointcut() {
    }

    @Pointcut("execution(@ru.openschool.timetrackingsystems.annotation.TrackAsyncTime * *(..))")
    public void asynchronTrackingPointcut() {
    }

    @Around("synchronTrackingPointcut()")
    public void synchronMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        timeTrack(joinPoint, false);
    }

    @Around("asynchronTrackingPointcut()")
    public Object asynchronMethod(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            try {
                timeTrack(joinPoint, true);
            } catch (Throwable e) {
                e.getStackTrace();
            }
        });
    }

    private void timeTrack(ProceedingJoinPoint joinPoint, boolean async) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        long startMillis = System.currentTimeMillis();
        long startNano = System.nanoTime();

        joinPoint.proceed();

        long endNano = System.nanoTime();
        long endMillis = System.currentTimeMillis();

        methodLogService.saveTracking(endNano - startNano, endMillis - startMillis,
                methodName, async);
    }
}
