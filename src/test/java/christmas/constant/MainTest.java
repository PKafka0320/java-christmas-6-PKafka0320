package christmas.constant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import org.junit.jupiter.api.Test;

class MainTest {

    @Test
    void findAppetizer() {
        // given
        String case1 = "바비큐립";
        String case2 = "";
        String case3 = "파스타";

        // when
        Main result1 = Main.findMain(case1);
        Main result2 = Main.findMain(case2);
        Main result3 = Main.findMain(case3);

        // then
        assertThat(result1).as("case1").isEqualTo(Main.BBQ_RIB);
        assertThat(result2).as("case2").isEqualTo(null);
        assertThat(result3).as("case3").isEqualTo(null);
    }
}