package christmas.constant;

import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Main {
    T_BONE_STEAK("티본스테이크", 55_000),
    BBQ_RIB("바비큐립", 54_000),
    SEAFOOD_PASTA("해산물파스타", 35_000),
    CHRISTMAS_PASTA("크리스마스파스타", 25_000);

    private final String name;
    private final int price;
    private static final Map<String, String> mainBundle = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(Main::getName, Main::name))
    );

    Main(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public static Main findMain(String name) throws NullPointerException {
        try {
            String mainName = mainBundle.get(name);
            if (mainName == null) {
                return null;
            }
            return Main.valueOf(mainName);
        } catch (NullPointerException e) {
            throw new NullPointerException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}