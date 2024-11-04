package cn.unminded.rtool.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Objects;

public final class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException();
    }

    public static final long SECONDS_1 = 1000l;
    public static final ZoneOffset DEFAULT_ZONE_OFFSET = ZoneOffset.of("+8");
    public static final ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_ZONE_OFFSET.getId());
    public static final Locale DEFAULT_LOCALE = Locale.CHINA;
    public static final Clock DEFAULT_CLOCK = Clock.system(DEFAULT_ZONE_ID);
    public static final DateTimeFormatter YMD_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            .withLocale(DEFAULT_LOCALE)
            .withZone(DEFAULT_ZONE_ID);
    public static final DateTimeFormatter YMD_HMS_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withLocale(DEFAULT_LOCALE).withZone(DEFAULT_ZONE_ID);
    public static final DateTimeFormatter YMD_HMS_3S_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withLocale(DEFAULT_LOCALE).withZone(DEFAULT_ZONE_ID);

    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }

    public static String ymd() {
        return YMD_FORMATTER.format(now());
    }

    public static String ymd(LocalDateTime localDateTime) {
        return YMD_FORMATTER.format(localDateTime);
    }

    public static String hms() {
        return YMD_HMS_FORMATTER.format(now());
    }

    public static String hms(LocalDateTime localDateTime) {
        return YMD_HMS_FORMATTER.format(localDateTime);
    }

    public static String ymdHms() {
        return YMD_HMS_FORMATTER.format(now());
    }

    public static String ymdHms(LocalDateTime localDateTime) {
        return YMD_HMS_FORMATTER.format(localDateTime);
    }

    public static String ymdHms3S(LocalDateTime localDateTime) {
        return YMD_HMS_3S_FORMATTER.format(localDateTime);
    }

    public static String ymdHms3S() {
        return YMD_HMS_3S_FORMATTER.format(now());
    }

    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(localDateTime);
    }

    public static String format(long time) {
        return format(time, YMD_HMS_FORMATTER);
    }

    public static String format(long time, DateTimeFormatter dateTimeFormatter) {
        return dateTimeFormatter.format(Instant.ofEpochMilli(time));
    }

    public static boolean isBefore(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return first.isBefore(second);
    }

    public static boolean isAfter(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return first.isAfter(second);
    }

    public static LocalDateTime plusYears(LocalDateTime dateTime, long years) {
        return dateTime.plusYears(years);
    }

    public static LocalDateTime plusMonths(LocalDateTime dateTime, long months) {
        return dateTime.plusMonths(months);
    }

    public static LocalDateTime plusDays(LocalDateTime dateTime, long days) {
        return dateTime.plusDays(days);
    }

    public static LocalDateTime plusMinutes(LocalDateTime dateTime, long minutes) {
        return dateTime.plusMinutes(minutes);
    }

    public static LocalDateTime plusSeconds(LocalDateTime dateTime, long seconds) {
        return dateTime.plusSeconds(seconds);
    }

    public static long diffYears(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return first.getYear() - second.getYear();
    }

    public static long diffMonths(LocalDateTime first, LocalDateTime second) {
        long diffYears = diffYears(first, second);
        long diffMonths = 0;
        if (diffYears == 0) {
            diffMonths = first.getMonth().getValue() - second.getMonth().getValue();
        } else {
            diffMonths = diffYears * 12 + (first.getMonth().getValue() - second.getMonth().getValue());
        }

        return diffMonths;
    }

    public static long diffDays(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return Duration.between(second, first).toDays();
    }

    public static long diffHours(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return Duration.between(second, first).toHours();
    }

    public static long diffMinutes(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return Duration.between(second, first).toMinutes();
    }

    public static long diffSeconds(LocalDateTime first, LocalDateTime second) {
        return diffMillis(first, second) / SECONDS_1;
    }

    public static long diffMillis(LocalDateTime first, LocalDateTime second) {
        Objects.requireNonNull(first, "first can't be null");
        Objects.requireNonNull(second, "second can't be null");

        return Duration.between(second, first).toMillis();
    }

    public static LocalDateTime parse(String dateTime) {
        return parse(dateTime, YMD_HMS_FORMATTER);
    }

    public static LocalDateTime parse(String dateTime, DateTimeFormatter dateTimeFormatter) {
        if (YMD_FORMATTER.equals(dateTimeFormatter)) {
            return dateTimeFormatter.parse(dateTime, LocalDate::from).atStartOfDay();
        } else if (YMD_HMS_FORMATTER.equals(dateTimeFormatter)) {
            return dateTimeFormatter.parse(dateTime, LocalDateTime::from);
        }

        return null;
    }

    public static Instant toInstant(String dateTime) {
        return parse(dateTime).toInstant(DEFAULT_ZONE_OFFSET);
    }

    public static Instant toInstant(String dateTime, DateTimeFormatter dateTimeFormatter) {
        return parse(dateTime, dateTimeFormatter).toInstant(DEFAULT_ZONE_OFFSET);
    }

}
