package christmas.validation;

import christmas.constant.ErrorMessage;

public class Validation {

    public static void validateDate(int date) throws IllegalArgumentException {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }
}
