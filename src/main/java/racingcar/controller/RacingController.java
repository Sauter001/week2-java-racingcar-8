package racingcar.controller;

import racingcar.domain.CarList;
import racingcar.domain.Race;
import racingcar.dto.InputDto;
import racingcar.view.View;

public class RacingController {
    private final View view;

    public RacingController(View view) {
        this.view = view;
    }

    public void run() {
        InputDto inputDto = view.inputRacingInfo();

        CarList carList = new CarList(inputDto.cars());
        Race race = new Race(carList, inputDto.round());
    }
}
