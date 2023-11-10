package christmas.domain;

import christmas.constant.Badge;
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
    BenefitDetail benefitDetail = new BenefitDetail();

    public AppetizerOrder getAppetizerOrder() {
        return this.appetizerOrder;
    }

    public BeverageOrder getBeverageOrder() {
        return this.beverageOrder;
    }

    public DessertOrder getDessertOrder() {
        return this.dessertOrder;
    }

    public MainOrder getMainOrder() {
        return this.mainOrder;
    }

    public BenefitDetail getBenefitDetail() {
        return this.benefitDetail;
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
        Validation.validateMenuCount(menuCount);
        Validation.validateOnlyBeverage(appetizerOrder, dessertOrder, mainOrder);
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