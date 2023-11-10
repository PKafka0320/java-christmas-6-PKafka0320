package christmas.domain;

import christmas.constant.Appetizer;
import christmas.constant.Badge;
import christmas.constant.Benefit;
import christmas.constant.Beverage;
import christmas.constant.Dessert;
import christmas.constant.Main;
import christmas.constant.Week;
import christmas.validation.Validation;
import java.util.HashMap;
import java.util.Map;

public class Receipt {

    private int date;
    private int totalPrice = 0;
    private int totalDiscount = 0;
    private int totalBenefit = 0;
    private Boolean event = false;
    Map<Appetizer, Integer> appetizerOrder = new HashMap<>();
    Map<Beverage, Integer> beverageOrder = new HashMap<>();
    Map<Dessert, Integer> dessertOrder = new HashMap<>();
    Map<Main, Integer> mainOrder = new HashMap<>();
    Map<Benefit, Integer> benefitAmount = new HashMap<>();

    public void setDate(int date) {
        Validation.validateDate(date);
        this.date = date;
    }

    public void calculateTotalPrice() {
        for (Appetizer appetizer : appetizerOrder.keySet()) {
            totalPrice += appetizer.getPrice() * appetizerOrder.get(appetizer);
        }
        for (Beverage beverage : beverageOrder.keySet()) {
            totalPrice += beverage.getPrice() * beverageOrder.get(beverage);
        }
        for (Dessert dessert : dessertOrder.keySet()) {
            totalPrice += dessert.getPrice() * dessertOrder.get(dessert);
        }
        for (Main main : mainOrder.keySet()) {
            totalPrice += main.getPrice() * mainOrder.get(main);
        }
    }

    public void checkBenefit() {
        if (totalPrice < 10_000) {
            return;
        }
        Week day = Week.findDay(date);

        calculateDDayDiscount();
        calculateWeekdayDiscount(day);
        calculateWeekendDiscount(day);
        calculateSpecialDiscount(day);
        checkEvent();
    }

    private void checkEvent() {
        totalBenefit = totalDiscount;
        if (totalPrice >= 120_000) {
            event = true;
            Benefit benefit = Benefit.EVENT;
            benefitAmount.put(benefit, benefitAmount.getOrDefault(benefit, 0) - Beverage.CHAMPAGNE.getPrice());
            totalBenefit -= Beverage.CHAMPAGNE.getPrice();
        }
    }

    private void calculateDDayDiscount() {
        if (date <= 25) {
            Benefit benefit = Benefit.D_DAY;
            int discount = 900 + (date * 100);
            totalDiscount -= discount;
            benefitAmount.put(benefit, benefitAmount.getOrDefault(benefit, 0) - discount);
        }
    }

    private void calculateWeekdayDiscount(Week day) {
        if (day != Week.FRIDAY && day != Week.SATURDAY) {
            Benefit benefit = Benefit.WEEKDAY;
            for (Dessert dessert : dessertOrder.keySet()) {
                int discount = dessertOrder.get(dessert) * 2030;
                totalDiscount -= discount;
                benefitAmount.put(benefit, benefitAmount.getOrDefault(benefit, 0) - discount);
            }
        }
    }

    private void calculateWeekendDiscount(Week day) {
        if (day == Week.FRIDAY || day == Week.SATURDAY) {
            Benefit benefit = Benefit.WEEKEND;
            for (Main main : mainOrder.keySet()) {
                int discount = mainOrder.get(main) * 2030;
                totalDiscount -= discount;
                benefitAmount.put(benefit, benefitAmount.getOrDefault(benefit, 0) - discount);
            }
        }
    }

    private void calculateSpecialDiscount(Week day) {
        if (day == Week.SUNDAY || date == 25) {
            Benefit benefit = Benefit.SPECIAL;
            int discount = 1000;
            totalDiscount -= discount;
            benefitAmount.put(benefit, benefitAmount.getOrDefault(benefit, 0) - discount);
        }
    }
}