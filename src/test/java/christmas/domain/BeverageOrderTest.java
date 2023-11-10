package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import christmas.constant.Beverage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BeverageOrderTest {

    private final BeverageOrder beverageOrder = new BeverageOrder();

    @BeforeEach
    void beforeEach() {
        beverageOrder.clear();
    }

    @Test
    void totalPrice() {
        // given
        beverageOrder.addOrder("제로콜라", 1);
        beverageOrder.addOrder("레드와인", 1);
        beverageOrder.addOrder("샴페인", 1);
        int sum = Beverage.RED_WINE.getPrice() + Beverage.CHAMPAGNE.getPrice() + Beverage.ZERO_COKE.getPrice();

        // when
        int result = beverageOrder.totalPrice();

        // then
        assertThat(result).isEqualTo(sum);
    }

    @Test
    void addOrder() {
        // given
        String nameCase1 = "제로콜라";
        String nameCase2 = "아무거나";
        int quantityCase1 = 3;
        int quantityCase2 = 0;

        // when
        int result1 = beverageOrder.addOrder(nameCase1, quantityCase1);
        Throwable result2 = catchThrowable(() -> {
            beverageOrder.addOrder(nameCase1, quantityCase2);
        });
        int result3 = beverageOrder.addOrder(nameCase2, quantityCase1);

        // then
        assertThat(result1).as("name 1, quantity 1").isEqualTo(3);
        assertThat(result2).as("name 1, quantity 2").isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).as("name 2, quantity 1").isEqualTo(0);
    }
}