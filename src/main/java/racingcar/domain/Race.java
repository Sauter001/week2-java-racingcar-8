package racingcar.domain;

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
            runRound(raceResult);
        }

        return raceResult;
    }

    private void runRound(RaceResult raceResult) {
        cars.moveAll();
        RoundResults roundResults = captureRoundResults();
        raceResult.addRoundResult(roundResults.toDto());
    }

    private RoundResults captureRoundResults() {
        return new RoundResults(cars.size(), cars.getRaceResult());
    }
}
