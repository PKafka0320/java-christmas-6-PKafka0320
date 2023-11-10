package christmas.domain;

import christmas.constant.Main;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainOrder {

    private final Map<Main, Integer> order = new HashMap<>();

    public Map<Main, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }

    public int addOrder(String menuName, int quantity) throws IllegalArgumentException {
        Main mainName = Main.findMain(menuName);
        if (mainName == null) {
            return 0;
        }
        Validation.validateOrderCount(quantity);
        if (order.containsKey(mainName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        order.put(mainName, quantity);
        return quantity;
    }

    public int totalPrice() {
        int sum = 0;
        for (Main main : order.keySet()) {
            sum += main.getPrice() * order.get(main);
        }
        return sum;
    }

    public int quantity() {
        int totalQuantity = 0;
        for (int quantity : getOrder().values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

    public boolean isEmpty() {
        return order.isEmpty();
    }

    public void clear() {
        order.clear();
    }
}