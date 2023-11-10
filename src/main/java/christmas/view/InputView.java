package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.ErrorMessage;
import christmas.validation.Validation;
import java.util.HashMap;
import java.util.Map;

public class InputView {

    public int readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        int date = -1;
        try {
            date = Integer.parseInt(Console.readLine());
            Validation.validateDate(date);
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
            String[] singleOrder = orderWithQuantity.split("-", -1);
            Validation.validateOrderFormat(singleOrder);
            try {
                order.put(singleOrder[0], Integer.parseInt(singleOrder[1]));
            } catch (IllegalArgumentException e) {
                System.out.println(ErrorMessage.INVALID_FORMAT.getMessage());
            }
        }
        return order;
    }
}