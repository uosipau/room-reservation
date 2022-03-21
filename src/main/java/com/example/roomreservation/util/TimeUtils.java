package com.example.roomreservation.util;

import java.time.LocalDateTime;

public class TimeUtils {

    public static boolean timeSpansIntersects(
            LocalDateTime start1, LocalDateTime end1,
            LocalDateTime start2, LocalDateTime end2
    ) {
        return (isInSpan(start2, start1, end1) || start2.isEqual(start1)) &&
                (isInSpan(end2, start1, start2) || end2.isEqual(end1));
    }

    private static boolean isInSpan(LocalDateTime time, LocalDateTime start, LocalDateTime end) {
        return time.isAfter(start) && time.isBefore(end);
    }

}
