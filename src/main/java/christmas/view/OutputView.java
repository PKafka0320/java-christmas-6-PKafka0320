package christmas.view;

import christmas.constant.Appetizer;
import christmas.constant.Badge;
import christmas.constant.Benefit;
import christmas.constant.Beverage;
import christmas.constant.Dessert;
import christmas.constant.Main;
import java.text.DecimalFormat;
import java.util.Map;

public class OutputView {

    static DecimalFormat formatter = new DecimalFormat("###,###");

    public void showMenu(Map<Appetizer, Integer> appetizerOrder, Map<Beverage, Integer> beverageOrder,
            Map<Dessert, Integer> dessertOrder, Map<Main, Integer> mainOrder) {
        System.out.println("12월 3일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n");
        System.out.println("<주문 메뉴>");
        printAppetizer(appetizerOrder);
        printBeverage(beverageOrder);
        printDessert(dessertOrder);
        printMain(mainOrder);
        System.out.println();
    }

    private void printMain(Map<Main, Integer> mainOrder) {
        for (Main main : mainOrder.keySet()) {
            System.out.println(main.getName() + " " + mainOrder.get(main) + "개");
        }
    }

    private void printDessert(Map<Dessert, Integer> dessertOrder) {
        for (Dessert dessert : dessertOrder.keySet()) {
            System.out.println(dessert.getName() + " " + dessertOrder.get(dessert) + "개");
        }
    }

    private void printBeverage(Map<Beverage, Integer> beverageOrder) {
        for (Beverage beverage : beverageOrder.keySet()) {
            System.out.println(beverage.getName() + " " + beverageOrder.get(beverage) + "개");
        }
    }

    private void printAppetizer(Map<Appetizer, Integer> appetizerOrder) {
        for (Appetizer appetizer : appetizerOrder.keySet()) {
            System.out.println(appetizer.getName() + " " + appetizerOrder.get(appetizer) + "개");
        }
    }

    public void showPriceBeforeDiscount(int price) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(formatter.format(price));
        System.out.println();
    }

    public void showEventMenu(boolean event) {
        System.out.println("<증정 메뉴>");
        if (event) {
            System.out.println("샴페인 1개");
            System.out.println();
            return;
        }
        System.out.println("없음");
        System.out.println();
    }

    public void showBenefit(Map<Benefit, Integer> benefitAmount) {
        System.out.println("<혜택 내역>");
        if (benefitAmount.isEmpty()) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        for (Benefit benefit : benefitAmount.keySet()) {
            int discount = benefitAmount.get(benefit) * -1;
            System.out.println(benefit.getDescription() + ": " + formatter.format(discount) + "원");
        }
        System.out.println();
    }

    public void showTotalBenefit(int totalBenefit) {
        System.out.println("<총혜택 금액>");
        int discount = totalBenefit * -1;
        System.out.println(formatter.format(discount) + "원");
        System.out.println();
    }

    public void showPrice(int totalPrice, int totalDiscount) {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(formatter.format(totalPrice - totalDiscount));
        System.out.println();
    }

    public void showBadge(Badge badge) {
        System.out.println("<12월 이벤트 배지>");
        if (badge == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(badge.getName());
    }
}