package christmas.domain;

import christmas.constant.Beverage;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BeverageOrder {

    private final Map<Beverage, Integer> order = new HashMap<>();

    public Map<Beverage, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }

    public int addOrder(String menuName, int quantity) throws IllegalArgumentException {
        Beverage beverageName = Beverage.findBeverage(menuName);
        if (beverageName == null) {
            return 0;
        }
        Validation.validateOrderCount(quantity);
        if (order.containsKey(beverageName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        order.put(beverageName, quantity);
        return quantity;
    }

    public int totalPrice() {
        int sum = 0;
        for (Beverage beverage : order.keySet()) {
            sum += beverage.getPrice() * order.get(beverage);
        }
        return sum;
    }

    public void clear() {
        order.clear();
    }
}