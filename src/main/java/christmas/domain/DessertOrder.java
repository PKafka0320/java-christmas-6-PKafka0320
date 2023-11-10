package christmas.domain;

import christmas.constant.Dessert;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DessertOrder {

    private final Map<Dessert, Integer> order = new HashMap<>();

    public Map<Dessert, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }

    public int addOrder(String menuName, int quantity) throws IllegalArgumentException {
        Dessert dessertName = Dessert.findDessert(menuName);
        if (dessertName == null) {
            return 0;
        }
        Validation.validateOrderCount(quantity);
        if (order.containsKey(dessertName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        order.put(dessertName, quantity);
        return quantity;
    }

    public int totalPrice() {
        int sum = 0;
        for (Dessert dessert : order.keySet()) {
            sum += dessert.getPrice() * order.get(dessert);
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