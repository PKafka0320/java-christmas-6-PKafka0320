package christmas;

import christmas.domain.Planner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Planner planner = new Planner();

    public void run() {
        askDate();
        askOrder();
        planner.makeResult();
        showResult();
    }

    private void showResult() {
        outputView.showMenu(planner.getAppetizerOrder(), planner.getBeverageOrder(), planner.getDessertOrder(),
                planner.getMainOrder());
        outputView.showPriceBeforeDiscount(planner.getTotalPrice());
        outputView.showEventMenu(planner.getEvent());
        outputView.showBenefit(planner.getBenefitAmount());
        outputView.showTotalBenefit(planner.getTotalBenefit());
        outputView.showPrice(planner.getTotalPrice(), planner.getTotalDiscount());
        outputView.showBadge(planner.getBadge());
    }

    private void askOrder() {
        while (true) {
            try {
                planner.setMenu(inputView.readOrder());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void askDate() {
        while (true) {
            try {
                planner.setDate(inputView.readDate());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}