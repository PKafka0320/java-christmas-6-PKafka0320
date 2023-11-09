package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class DessertTest {

    @Test
    void findDessert() {
        // given
        String case1 = "초코케이크";
        String case2 = "";
        String case3 = "아이스";

        // when
        Dessert result1 = Dessert.findDessert(case1);
        Throwable result2 = catchThrowable(() -> {
            Dessert.findDessert(case2);
        });
        Throwable result3 = catchThrowable(() -> {
            Dessert.findDessert(case3);
        });

        // then
        assertThat(result1).as("case1").isEqualTo(Dessert.CHOCO_CAKE);
        assertThat(result2).as("case2").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        assertThat(result3).as("case3").isInstanceOf(NullPointerException.class)
                .hasMessage("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }
}