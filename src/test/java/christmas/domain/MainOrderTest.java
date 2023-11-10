package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import christmas.constant.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainOrderTest {

    private final MainOrder mainOrder = new MainOrder();

    @BeforeEach
    void beforeEach() {
        mainOrder.clear();
    }

    @Test
    void totalPrice() {
        // given
        mainOrder.addOrder("티본스테이크", 1);
        mainOrder.addOrder("바비큐립", 1);
        mainOrder.addOrder("해산물파스타", 1);
        mainOrder.addOrder("크리스마스파스타", 1);
        int sum = Main.BBQ_RIB.getPrice() + Main.CHRISTMAS_PASTA.getPrice() + Main.SEAFOOD_PASTA.getPrice()
                + Main.T_BONE_STEAK.getPrice();

        // when
        int result = mainOrder.totalPrice();

        // then
        assertThat(result).isEqualTo(sum);
    }

    @Test
    void addOrder() {
        // given
        String nameCase1 = "티본스테이크";
        String nameCase2 = "아무거나";
        int quantityCase1 = 3;
        int quantityCase2 = 0;

        // when
        int result1 = mainOrder.addOrder(nameCase1, quantityCase1);
        Throwable result2 = catchThrowable(() -> {
            mainOrder.addOrder(nameCase1, quantityCase2);
        });
        int result3 = mainOrder.addOrder(nameCase2, quantityCase1);

        // then
        assertThat(result1).as("name 1, quantity 1").isEqualTo(3);
        assertThat(result2).as("name 1, quantity 2").isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).as("name 2, quantity 1").isEqualTo(0);
    }
}