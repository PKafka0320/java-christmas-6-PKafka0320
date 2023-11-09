package christmas.constant;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class AppetizerTest {

    @Test
    void findAppetizer() {
        // given
        String case1 = "양송이수프";
        String case2 = "";
        String case3 = "시져샐러드";

        // when
        Appetizer result1 = Appetizer.findAppetizer(case1);
        Throwable result2 = catchThrowable(() -> {
            Appetizer.findAppetizer(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            Appetizer.findAppetizer(case3);
        });

        // then
        assertThat(result1).as("case1").isEqualTo(Appetizer.MUSHROOM_SOUP);
        assertThat(result2).as("case2").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        assertThat(result3).as("case3").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}