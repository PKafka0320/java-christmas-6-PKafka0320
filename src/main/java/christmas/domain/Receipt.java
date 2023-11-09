package christmas.domain;

import christmas.validation.Validation;

public class Receipt {

    private int date;

    public void setDate(int date) {
        Validation.validateDate(date);
        this.date = date;
    }
}