package christmas.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Beverage {

    ZERO_COKE("제로콜라", 3_000),
    RED_WINE("레드와인", 60_000),
    CHAMPAGNE("샴페인", 25_000);

    private final String name;
    private final int price;
    private static final Map<String, String> dessertBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Beverage::getName, Beverage::name))
    );

    Beverage(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Beverage findBeverage(String name) throws NullPointerException {
        try {
            return Beverage.valueOf(dessertBundle.get(name));
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}