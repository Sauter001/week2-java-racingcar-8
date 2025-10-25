package racingcar.domain;

public class Race {
    private CarList carList;
    private NumOfRounds numOfRounds;

    public Race(CarList carList, String numOfRounds) {
        this.carList = carList;
        this.numOfRounds = new NumOfRounds(numOfRounds);
    }
}
