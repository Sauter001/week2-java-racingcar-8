package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.exception.common.ListEmptyException;
import racingcar.exception.domain.CarNameAlreadyExistsException;
import racingcar.exception.domain.CarNameEmptyException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Cars {
    private List<Car> carList;

    public Cars(String carsInput) {
        this.carList = convertToCarList(carsInput);
    }

    public void moveAll() {
        for (Car car : carList) {
            car.move();
        }
    }

    public int size() {
        return carList.size();
    }

    public List<CarDto> findWinners() {
        List<CarDto> carDtos = this.carList.stream().map(Car::toDto).toList();
        int maxPosition = findMaxPosition(carDtos);

        return carDtos.stream()
                .filter(carDto -> carDto.position() == maxPosition)
                .toList();
    }

    public List<CarDto> getRaceResult() {
        return carList.stream().map(Car::toDto).toList();
    }

    private int findMaxPosition(List<CarDto> carDtos) {
        return carDtos.stream().mapToInt(CarDto::position).max().orElse(0);
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
