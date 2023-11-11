package christmas;

import christmas.domain.BenefitDetail;
import christmas.domain.Planner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Controller {

    InputView inputView = new InputView();
    OutputView outputView = new OutputView();
    Planner planner;

    public void run() {
        BenefitDetail benefitDetail = new BenefitDetail();
        planner = new Planner(benefitDetail);

        askDate();
        askOrder();
        planner.makeResult();
        outputView.showResult(planner, benefitDetail);
    }

    private void askOrder() {
        while (true) {
            try {
                planner.setMenu(inputView.readOrder());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                planner.clearOrder();
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