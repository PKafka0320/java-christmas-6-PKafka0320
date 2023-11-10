package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlannerTest {

    private Planner planner;

    @BeforeEach
    void beforeEach() {
        planner = new Planner();
    }

    @Test
    void setDate() {
        // given
        int case1 = 1;
        int case2 = 15;
        int case3 = -1;
        int case4 = 50;

        // when
        Throwable result1 = catchThrowable(() -> {
            planner.setDate(case1);
        });
        Throwable result2 = catchThrowable(() -> {
            planner.setDate(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            planner.setDate(case3);
        });
        Throwable result4 = catchThrowable(() -> {
            planner.setDate(case4);
        });

        // then
        assertThat(result1).as("case1").doesNotThrowAnyException();
        assertThat(result2).as("case2").doesNotThrowAnyException();
        assertThat(result3).as("case3").isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        assertThat(result4).as("case4").isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }
}