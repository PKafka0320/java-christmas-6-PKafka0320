package christmas.domain;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.catchThrowable;

import christmas.constant.Appetizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AppetizerOrderTest {

    private final AppetizerOrder appetizerOrder = new AppetizerOrder();

    @BeforeEach
    void beforeEach() {
        appetizerOrder.clear();
    }

    @Test
    void totalPrice() {
        // given
        appetizerOrder.addOrder("양송이수프", 1);
        appetizerOrder.addOrder("타파스", 1);
        appetizerOrder.addOrder("시저샐러드", 1);
        int sum = Appetizer.MUSHROOM_SOUP.getPrice() + Appetizer.CAESAR_SALAD.getPrice() + Appetizer.TAPAS.getPrice();

        // when
        int result = appetizerOrder.totalPrice();

        // then
        assertThat(result).isEqualTo(sum);
    }

    @Test
    void addOrder() {
        // given
        String nameCase1 = "양송이수프";
        String nameCase2 = "아무거나";
        int quantityCase1 = 3;
        int quantityCase2 = 0;

        // when
        int result1 = appetizerOrder.addOrder(nameCase1, quantityCase1);
        Throwable result2 = catchThrowable(() -> {
            appetizerOrder.addOrder(nameCase1, quantityCase2);
        });
        int result3 = appetizerOrder.addOrder(nameCase2, quantityCase1);

        // then
        assertThat(result1).as("name 1, quantity 1").isEqualTo(3);
        assertThat(result2).as("name 1, quantity 2").isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).as("name 2, quantity 1").isEqualTo(0);
    }
}