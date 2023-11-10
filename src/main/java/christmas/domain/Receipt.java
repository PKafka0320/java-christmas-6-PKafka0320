package christmas.domain;

import christmas.constant.Appetizer;
import christmas.constant.Badge;
import christmas.constant.Benefit;
import christmas.constant.Beverage;
import christmas.constant.Dessert;
import christmas.constant.ErrorMessage;
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
    private Badge badge = null;
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

    public void setMenu(Map<String, Integer> orders) throws IllegalArgumentException {
        int menuCount = 0;
        for (String menu : orders.keySet()) {
            int count = 0;
            count += checkAppetizer(orders, menu);
            count += checkBeverage(orders, menu);
            count += checkDessert(orders, menu);
            count += checkMain(orders, menu);
            Validation.validateOrderCount(count);
        }
        Validation.validateMenuCount(menuCount);
        Validation.validateOnlyBeverage(appetizerOrder, dessertOrder, mainOrder);
    }

    private int checkMain(Map<String, Integer> orders, String menu) {
        int count = 0;
        Main main = Main.findMain(menu);
        if (main != null) {
            mainOrder.put(main, orders.get(menu));
            count++;
        }
        return count;
    }

    private int checkDessert(Map<String, Integer> orders, String menu) {
        int count = 0;
        Dessert dessert = Dessert.findDessert(menu);
        if (dessert != null) {
            dessertOrder.put(dessert, orders.get(menu));
            count++;
        }
        return count;
    }

    private int checkBeverage(Map<String, Integer> orders, String menu) {
        int count = 0;
        Beverage beverage = Beverage.findBeverage(menu);
        if (beverage != null) {
            beverageOrder.put(beverage, orders.get(menu));
            count++;
        }
        return count;
    }

    private int checkAppetizer(Map<String, Integer> orders, String menu) {
        int count = 0;
        Appetizer appetizer = Appetizer.findAppetizer(menu);
        if (appetizer != null) {
            appetizerOrder.put(appetizer, orders.get(menu));
            count++;
        }
        return count;
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
        checkBadge();
    }

    private void checkBadge() {
        if (totalBenefit >= 20_000) {
            badge = Badge.SANTA;
            return;
        }
        if (totalBenefit >= 10_000) {
            badge = Badge.TREE;
            return;
        }
        if (totalBenefit >= 5_000) {
            badge = Badge.STAR;
        }
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