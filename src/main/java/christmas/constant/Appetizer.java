package christmas.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Appetizer {

    MUSHROOM_SOUP("양송이수프", 6_000),
    TAPAS("타파스", 5_500),
    CAESAR_SALAD("시저샐러드", 8_000);

    private final String name;
    private final int price;
    private static final Map<String, String> appetizerBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Appetizer::getName, Appetizer::name))
    );

    Appetizer(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Appetizer findAppetizer(String name) throws NullPointerException {
        try {
            return Appetizer.valueOf(appetizerBundle.get(name));
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}