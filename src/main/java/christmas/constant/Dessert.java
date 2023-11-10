package christmas.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Dessert {

    CHOCO_CAKE("초코케이크", 15_000),
    ICE_CREAM("아이스크림", 5_000);

    private final String name;
    private final int price;
    private static final Map<String, String> dessertBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Dessert::getName, Dessert::name))
    );

    Dessert(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Dessert findDessert(String name) throws NullPointerException {
        try {
            String dessertName = dessertBundle.get(name);
            if (dessertName == null) {
                return null;
            }
            return Dessert.valueOf(dessertName);
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}