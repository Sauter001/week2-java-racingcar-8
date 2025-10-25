package racingcar.domain;

import racingcar.exception.domain.CarNameAlreadyExistsException;
import racingcar.exception.domain.CarNameEmptyException;
import racingcar.exception.common.ListEmptyException;

import java.util.*;

public class CarList {
    private List<Car> carList;

    public CarList(String carsInput) {
        this.carList = convertToCarList(carsInput);
    }

    private List<Car> convertToCarList(String carsInput) {
        List<String> carNames = Arrays.asList(carsInput.split(","));
        List<Car> carList = new ArrayList<>();
        HashSet<String> carNameSet = new HashSet<>();

        // 자동차 입력이 ,로 끝나면 예외 처리
        if (carsInput.endsWith(",")) {
            throw new CarNameEmptyException();
        }

        if (carNames.isEmpty()) {
            throw new ListEmptyException("CarNames");
        }

        for (String carName : carNames) {
            String trimmedCarName = carName.trim();

            checkCarNameDuplication(carNameSet, trimmedCarName);
            carList.add(new Car(trimmedCarName));
            carNameSet.add(carName);
        }

        return carList;
    }

    private void checkCarNameDuplication(HashSet<String> carNameSet, String trimmedCarName) {
        if (carNameSet.contains(trimmedCarName)) {
            throw new CarNameAlreadyExistsException(trimmedCarName);
        }
    }
}
