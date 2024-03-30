package ru.openschool.timetrackingsystems.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Используйте в ваших методах аннотацию @TrackTime
 * для синхронного отслеживания времени их выполнения.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TrackTime {
}
