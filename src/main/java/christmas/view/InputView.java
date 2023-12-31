package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.HashMap;
import java.util.Map;

public class InputView {

    private static final int MENU_INDEX = 0;
    private static final int QUANTITY_INDEX = 1;

    public int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int date;
        try {
            date = Integer.parseInt(Console.readLine());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
        return date;
    }

    public Map<String, Integer> readOrder() {
        Map<String, Integer> order = new HashMap<>();
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        String orders = Console.readLine();
        for (String orderWithQuantity : orders.split(",", -1)) {
            makeOrder(orderWithQuantity, order);
        }
        return order;
    }

    private void makeOrder(String orderWithQuantity, Map<String, Integer> order) {
        String[] singleOrder = orderWithQuantity.split("-", -1);
        try {
            Validation.validateOrderFormat(singleOrder);
            Validation.validateOrderDuplication(order, singleOrder[MENU_INDEX]);
            order.put(singleOrder[MENU_INDEX], Integer.parseInt(singleOrder[QUANTITY_INDEX]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_FORMAT.getMessage());
        }
    }
}