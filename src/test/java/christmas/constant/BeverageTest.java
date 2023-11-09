package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class BeverageTest {

    @Test
    void findBeverage() {
        // given
        String case1 = "샴페인";
        String case2 = "";
        String case3 = "콜라";

        // when
        Beverage result1 = Beverage.findBeverage(case1);
        Throwable result2 = catchThrowable(() -> {
            Beverage.findBeverage(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            Beverage.findBeverage(case3);
        });

        // then
        assertThat(result1).as("case1").isEqualTo(Beverage.CHAMPAGNE);
        assertThat(result2).as("case2").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        assertThat(result3).as("case3").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}