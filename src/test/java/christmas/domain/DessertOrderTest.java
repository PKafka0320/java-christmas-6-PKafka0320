package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import christmas.constant.Dessert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DessertOrderTest {

    private final DessertOrder dessertOrder = new DessertOrder();

    @BeforeEach
    void beforeEach() {
        dessertOrder.clear();
    }

    @Test
    void totalPrice() {
        // given
        dessertOrder.addOrder("초코케이크", 1);
        dessertOrder.addOrder("아이스크림", 1);
        int sum = Dessert.CHOCO_CAKE.getPrice() + Dessert.ICE_CREAM.getPrice();

        // when
        int result = dessertOrder.totalPrice();

        // then
        assertThat(result).isEqualTo(sum);
    }

    @Test
    void addOrder() {
        // given
        String nameCase1 = "초코케이크";
        String nameCase2 = "아무거나";
        int quantityCase1 = 3;
        int quantityCase2 = 0;

        // when
        int result1 = dessertOrder.addOrder(nameCase1, quantityCase1);
        Throwable result2 = catchThrowable(() -> {
            dessertOrder.addOrder(nameCase1, quantityCase2);
        });
        int result3 = dessertOrder.addOrder(nameCase2, quantityCase1);

        // then
        assertThat(result1).as("name 1, quantity 1").isEqualTo(3);
        assertThat(result2).as("name 1, quantity 2").isInstanceOf(IllegalArgumentException.class);
        assertThat(result3).as("name 2, quantity 1").isEqualTo(0);
    }
}