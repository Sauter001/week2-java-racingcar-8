package racingcar;

import racingcar.controller.RacingController;
import racingcar.view.ConsoleView;
import racingcar.view.View;

public class Application {
    public static void main(String[] args) {
        View view = new ConsoleView();

        RacingController controller = new RacingController(view);
        controller.run();
    }
}
