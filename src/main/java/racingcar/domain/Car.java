package racingcar.domain;

import racingcar.dto.CarDto;

public class Car {
    private final CarName carName;
    private int position = 0;

    public Car(String carName) {
        this.carName = new CarName(carName);
    }

    public void move() {
        position++;
    }

    public CarDto toDto() {
        return new CarDto(carName.toString(), position);
    }
}
