package christmas.domain;

import christmas.constant.Appetizer;
import christmas.constant.Badge;
import christmas.constant.Beverage;
import christmas.constant.Dessert;
import christmas.constant.Main;
import christmas.validation.Validation;
import java.util.Map;

public class Planner {

    private int date;
    private int totalPrice = 0;
    private Badge badge = null;
    AppetizerOrder appetizerOrder = new AppetizerOrder();
    BeverageOrder beverageOrder = new BeverageOrder();
    DessertOrder dessertOrder = new DessertOrder();
    MainOrder mainOrder = new MainOrder();
    BenefitDetail benefitDetail;

    public Planner(BenefitDetail benefitDetail) {
        this.benefitDetail = benefitDetail;
    }

    public Map<Appetizer, Integer> getAppetizerOrder() {
        return this.appetizerOrder.getOrder();
    }

    public Map<Beverage, Integer> getBeverageOrder() {
        return this.beverageOrder.getOrder();
    }

    public Map<Dessert, Integer> getDessertOrder() {
        return this.dessertOrder.getOrder();
    }

    public int getDessertQuantity() {
        return this.dessertOrder.quantity();
    }

    public Map<Main, Integer> getMainOrder() {
        return this.mainOrder.getOrder();
    }

    public int getMainQuantity() {
        return this.mainOrder.quantity();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public Badge getBadge() {
        return badge;
    }

    public int getDate() {
        return this.date;
    }

    public void setDate(int date) {
        Validation.validateDate(date);
        this.date = date;
    }

    public void setMenu(Map<String, Integer> orders) throws IllegalArgumentException {
        System.out.println(orders);
        int orderCount = makeOrder(orders);
        Validation.validateTotalOrderCount(orderCount);
        Validation.validateOnlyBeverage(appetizerOrder, dessertOrder, mainOrder);
    }

    private int makeOrder(Map<String, Integer> orders) {
        int menuCount = 0;
        for (String menu : orders.keySet()) {
            int count = 0;
            count += appetizerOrder.addOrder(menu, orders.get(menu));
            count += beverageOrder.addOrder(menu, orders.get(menu));
            count += dessertOrder.addOrder(menu, orders.get(menu));
            count += mainOrder.addOrder(menu, orders.get(menu));
            Validation.validateOrderCount(count);
            menuCount += count;
        }
        return menuCount;
    }

    public void makeResult() {
        calculateTotalPrice();
        benefitDetail.checkBenefits(this);
        badge = Badge.checkBadge(benefitDetail.totalBenefit());
    }

    private void calculateTotalPrice() {
        totalPrice += appetizerOrder.totalPrice();
        totalPrice += beverageOrder.totalPrice();
        totalPrice += dessertOrder.totalPrice();
        totalPrice += mainOrder.totalPrice();
    }

    public void clearOrder() {
        appetizerOrder.clear();
        beverageOrder.clear();
        dessertOrder.clear();
        mainOrder.clear();
    }
}