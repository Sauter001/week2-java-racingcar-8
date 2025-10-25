package racingcar.domain;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.dto.CarDto;

public class Car {
    private static final int RANDOM_NUMBER_START = 0;
    private static final int RANDOM_NUMBER_END = 9;
    private static final int MOVE_THRESHOLD = 4;

    private final CarName carName;
    private int position = 0;

    public Car(String carName) {
        this.carName = new CarName(carName);
    }

    public void move() {
        int randomNumber = Randoms.pickNumberInRange(RANDOM_NUMBER_START, RANDOM_NUMBER_END);
        if (randomNumber >= MOVE_THRESHOLD) {
            position++;
        }
    }

    public CarDto toDto() {
        return new CarDto(carName.toString(), position);
    }
}
