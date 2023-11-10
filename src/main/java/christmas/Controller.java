package christmas;

import christmas.domain.Receipt;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Receipt receipt = new Receipt();

    public void run() {
        askDate();
        askOrder();
        receipt.makeResult();
        outputView.showMenu(receipt.getAppetizerOrder(), receipt.getBeverageOrder(), receipt.getDessertOrder(),
                receipt.getMainOrder());
        outputView.showPriceBeforeDiscount(receipt.getTotalPrice());
        outputView.showEventMenu(receipt.getEvent());
        outputView.showBenefit(receipt.getBenefitAmount());
        outputView.showTotalBenefit(receipt.getTotalBenefit());
        outputView.showPrice(receipt.getTotalPrice(), receipt.getTotalDiscount());
        outputView.showBadge(receipt.getBadge());
    }

    private void askOrder() {
        while (true) {
            try {
                receipt.setMenu(inputView.readOrder());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void askDate() {
        while (true) {
            try {
                receipt.setDate(inputView.readDate());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}