package christmas.domain;

import christmas.constant.Appetizer;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AppetizerOrder {

    private final Map<Appetizer, Integer> order = new HashMap<>();

    public Map<Appetizer, Integer> getOrder() {
        return Collections.unmodifiableMap(order);
    }

    public int addOrder(String menuName, int quantity) throws IllegalArgumentException {
        Appetizer appetizerName = Appetizer.findAppetizer(menuName);
        if (appetizerName == null) {
            return 0;
        }
        Validation.validateOrderCount(quantity);
        if (order.containsKey(appetizerName)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
        order.put(appetizerName, quantity);
        return quantity;
    }

    public int totalPrice() {
        int sum = 0;
        for (Appetizer appetizer : order.keySet()) {
            sum += appetizer.getPrice() * order.get(appetizer);
        }
        return sum;
    }

    public boolean isEmpty() {
        return order.isEmpty();
    }

    public void clear() {
        order.clear();
    }
}