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
        Dessert result2 = Dessert.findDessert(case2);
        Dessert result3 = Dessert.findDessert(case3);

        // then
        assertThat(result1).as("case1").isEqualTo(Dessert.CHOCO_CAKE);
        assertThat(result2).as("case2").isEqualTo(null);
        assertThat(result3).as("case3").isEqualTo(null);
    }
}