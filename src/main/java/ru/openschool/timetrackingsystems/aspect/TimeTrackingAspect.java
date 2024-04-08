package ru.openschool.timetrackingsystems.aspect;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import ru.openschool.timetrackingsystems.service.MethodLogService;

@Component
@Aspect
@RequiredArgsConstructor
public class TimeTrackingAspect {
    private final MethodLogService methodLogService;

    @Pointcut("execution(@ru.openschool.timetrackingsystems.annotation.TrackTime * *(..))")
    public void synchronTrackingPointcut() {
    }

    @Pointcut("execution(@ru.openschool.timetrackingsystems.annotation.TrackAsyncTime * *(..))")
    public void asynchronTrackingPointcut() {
    }

    @Around("synchronTrackingPointcut()")
    public Object synchronMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startMillis = System.currentTimeMillis();
        long startNano = System.nanoTime();

        String methodName = proceedingJoinPoint.getSignature().getName();
        Object result = proceedingJoinPoint.proceed();

        long endNano = System.nanoTime();
        long endMillis = System.currentTimeMillis();
        methodLogService.saveTracking(endNano - startNano, endMillis - startMillis,
                methodName, false);
        return result;
    }
}
