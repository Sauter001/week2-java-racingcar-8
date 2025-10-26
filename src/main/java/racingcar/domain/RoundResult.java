package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RoundResultDto;
import racingcar.exception.ErrorMessages;
import racingcar.exception.common.LengthInvalidException;

import java.util.List;
import java.util.Objects;

public class RoundResult {
    private final List<CarDto> carStates;

    public RoundResult(int numOfCars, List<CarDto> carStates) {
        validateSize(numOfCars, carStates);
        this.carStates = carStates;
    }

    public List<CarDto> findCarsOnMaxPosition() {
        int maxPosition = findMaxPosition();

        return this.carStates.stream()
                .filter(carDto -> carDto.position() == maxPosition)
                .toList();
    }

    public RoundResultDto toDto() {
        return new RoundResultDto(carStates);
    }

    private int findMaxPosition() {
        return this.carStates.stream()
                .mapToInt(CarDto::position)
                .max()
                .orElse(0);
    }

    private void validateSize(int numOfCars, List<CarDto> carStates) {
        if (numOfCars != Objects.requireNonNull(carStates).size()) {
            throw new LengthInvalidException(ErrorMessages.RACING_CAR_COUNT_MISMATCH);
        }
    }
}
