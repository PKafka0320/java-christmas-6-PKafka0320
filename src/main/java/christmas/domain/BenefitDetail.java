package christmas.domain;

import christmas.constant.Benefit;
import christmas.constant.Beverage;
import christmas.constant.Week;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BenefitDetail {

    private final Map<Benefit, Integer> benefits = new HashMap<>();

    public Map<Benefit, Integer> getDetail() {
        return Collections.unmodifiableMap(benefits);
    }

    public int totalBenefit() {
        int sum = 0;
        for (Benefit benefit : benefits.keySet()) {
            sum += benefits.get(benefit);
        }
        return sum;
    }

    public int totalDiscount() {
        int sum = 0;
        for (Benefit benefit : benefits.keySet()) {
            if (benefit == Benefit.EVENT) {
                continue;
            }
            sum += benefits.get(benefit);
        }
        return sum;
    }

    public boolean hasEvent() {
        return benefits.containsKey(Benefit.EVENT);
    }

    public void checkBenefits(Planner planner) {
        int date = planner.getDate();
        if (planner.getTotalPrice() < 10_000) {
            return;
        }
        Week day = Week.findDay(date);

        checkDDay(date);
        checkWeek(planner, day);
        checkSpecial(day, date);
        checkEvent(planner);
    }

    private void checkEvent(Planner planner) {
        if (planner.getTotalPrice() >= 120_000) {
            Benefit benefit = Benefit.EVENT;
            benefits.put(benefit, Beverage.CHAMPAGNE.getPrice());
        }
    }

    private void checkSpecial(Week day, int date) {
        if (day == Week.SUNDAY || date == 25) {
            Benefit benefit = Benefit.SPECIAL;
            int discount = 1000;
            benefits.put(benefit, discount);
        }
    }

    private void checkWeek(Planner planner, Week day) {
        if (day != Week.FRIDAY && day != Week.SATURDAY) {
            Benefit benefit = Benefit.WEEKDAY;
            int discount = planner.getDessertQuantity() * 2023;
            benefits.put(benefit, discount);
            return;
        }
        Benefit benefit = Benefit.WEEKEND;
        int discount = planner.getMainQuantity() * 2023;
        benefits.put(benefit, discount);
    }

    private void checkDDay(int date) {
        if (date <= 25) {
            Benefit benefit = Benefit.D_DAY;
            int discount = 900 + (date * 100);
            benefits.put(benefit, discount);
        }
    }
}