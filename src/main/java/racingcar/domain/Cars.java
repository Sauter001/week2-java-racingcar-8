package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.exception.ErrorMessages;
import racingcar.exception.common.ListEmptyException;
import racingcar.exception.domain.CarNameAlreadyExistsException;
import racingcar.parser.CarNameParser;

import java.util.HashSet;
import java.util.List;

public class Cars {
    private final List<Car> carList;

    public Cars(String carsInput) {
        CarNameParser carNameParser = new CarNameParser();
        List<String> carNames = carNameParser.parseCars(carsInput);
        this.carList = createCars(carNames);
    }

    private List<Car> createCars(List<String> carNames) {
        if (carNames.isEmpty()) {
            throw new ListEmptyException("CarNames");
        }

        checkCarNameDuplication(carNames);
        return carNames.stream().map(Car::new).toList();
    }

    public void moveAll() {
        for (Car car : carList) {
            car.move();
        }
    }

    public int size() {
        return carList.size();
    }

    public List<CarDto> getRaceResult() {
        return carList.stream().map(Car::toDto).toList();
    }

    private void checkCarNameDuplication(List<String> carNames) {
        HashSet<String> carNameSet = new HashSet<>(carNames);

        // 중복 자동차명이 존재하면 예외
        if (carNameSet.size() != carNames.size()) {
            throw new CarNameAlreadyExistsException(ErrorMessages.CAR_NAME_DUPLICATE);
        }
    }
}
