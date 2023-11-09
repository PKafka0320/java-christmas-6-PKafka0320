package christmas.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Week {

    MONDAY(4),
    TUESDAY(5),
    WEDNESDAY(6),
    THURSDAY(7),
    FRIDAY(1),
    SATURDAY(2),
    SUNDAY(3);

    private final int value;
    private static final Map<Integer, String> WeekBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Week::getValue, Week::name))
    );

    Week(int value) {
        this.value = value;
    }

    private int getValue() {
        return this.value;
    }

    public static Week findDay(int date) {
        validateDate(date);
        return Week.valueOf(WeekBundle.get(date % 7));
    }

    private static void validateDate(int date) throws IllegalArgumentException {
        if (date < 0 || date > 31) {
            throw new IllegalArgumentException(String.valueOf(ErrorMessage.INVALID_DATE));
        }
    }
}