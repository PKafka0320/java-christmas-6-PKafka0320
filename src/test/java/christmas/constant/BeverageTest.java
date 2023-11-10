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
        Beverage result2 = Beverage.findBeverage(case2);
        Beverage result3 = Beverage.findBeverage(case3);

        // then
        assertThat(result1).as("case1").isEqualTo(Beverage.CHAMPAGNE);
        assertThat(result2).as("case2").isEqualTo(null);
        assertThat(result3).as("case3").isEqualTo(null);
    }
}