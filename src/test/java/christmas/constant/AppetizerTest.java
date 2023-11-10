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
        Appetizer result2 = Appetizer.findAppetizer(case2);
        Appetizer result3 = Appetizer.findAppetizer(case3);

        // then
        assertThat(result1).as("case1").isEqualTo(Appetizer.MUSHROOM_SOUP);
        assertThat(result2).as("case2").isEqualTo(null);
        assertThat(result3).as("case3").isEqualTo(null);
    }
}