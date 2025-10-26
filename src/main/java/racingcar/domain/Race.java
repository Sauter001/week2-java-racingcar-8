package racingcar.domain;

import racingcar.dto.CarDto;

import java.util.List;

public class Race {
    private final Cars cars;
    private final NumOfRounds numOfRounds;

    public Race(Cars cars, String numOfRounds) {
        this.cars = cars;
        this.numOfRounds = new NumOfRounds(numOfRounds);
    }

    public RaceResult start() {
        RaceResult raceResult = new RaceResult(numOfRounds);

        for (int i = 0; i < this.numOfRounds.toInteger(); ++i) {
            cars.moveAll();
            RoundResults roundResults = new RoundResults(cars.size(), cars.getRaceResult());
            raceResult.addRoundResult(roundResults.toDto());
        }

        return raceResult;
    }
}
