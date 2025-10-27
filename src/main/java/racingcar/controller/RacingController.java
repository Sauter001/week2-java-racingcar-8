package racingcar.controller;

import racingcar.domain.Cars;
import racingcar.domain.Race;
import racingcar.domain.RaceResult;
import racingcar.dto.InputDto;
import racingcar.view.View;

public class RacingController {
    private final View view;

    public RacingController(View view) {
        this.view = view;
    }

    public void run() {
        InputDto inputDto = view.inputRacingInfo();

        Cars cars = new Cars(inputDto.cars());
        Race race = new Race(cars, inputDto.round());
        RaceResult raceResults = race.start();
        view.printRacingResult(raceResults.toDto());
        view.closeInput();
    }
}
