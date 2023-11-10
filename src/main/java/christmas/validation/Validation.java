package christmas.validation;

import christmas.constant.Appetizer;
import christmas.constant.Dessert;
import christmas.constant.ErrorMessage;
import christmas.constant.Main;
import java.util.Map;

public class Validation {

    public static void validateDate(int date) throws IllegalArgumentException {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static void validateMenuCount(int menuCount) throws IllegalArgumentException {
        if (menuCount < 1 || menuCount > 20) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    public static void validateOrderCount(int orderCount) throws IllegalArgumentException {
        if (orderCount < 1) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    public static void validateOnlyBeverage(Map<Appetizer, Integer> appetizerOrder,
            Map<Dessert, Integer> dessertOrder, Map<Main, Integer> mainOrder) throws IllegalArgumentException {
        if (appetizerOrder.isEmpty() && dessertOrder.isEmpty() && mainOrder.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }

    public static void validateOrderFormat(String[] singleOrder) {
        if (singleOrder.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}
