package christmas.constant;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class WeekTest {

    @Test
    void findDay() {
        // given
        int case1 = 1;
        int case2 = 15;
        int case3 = -1;
        int case4 = 50;

        // when
        Week result1 = Week.findDay(case1);
        Week result2 = Week.findDay(case2);
        Throwable result3 = catchThrowable(() -> {
            Week.findDay(case3);
        });
        Throwable result4 = catchThrowable(() -> {
            Week.findDay(case4);
        });

        // then
        assertThat(result1).as("case1").isEqualTo(Week.FRIDAY);
        assertThat(result2).as("case2").isEqualTo(Week.FRIDAY);
        assertThat(result3).as("case3").isInstanceOf(IllegalArgumentException.class);
        assertThat(result4).as("case4").isInstanceOf(IllegalArgumentException.class);
    }
}