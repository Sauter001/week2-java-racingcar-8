package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RoundResultDto;
import racingcar.exception.ErrorMessages;
import racingcar.exception.common.LengthInvalidException;

import java.util.List;
import java.util.Objects;

public class RoundResults {
    private final List<CarDto> carStates;

    public RoundResults(int numOfCars, List<CarDto> carStates) {
        validateSize(numOfCars, carStates);
        this.carStates = carStates;
    }

    private void validateSize(int numOfCars, List<CarDto> carStates) {
        if (numOfCars != Objects.requireNonNull(carStates).size()) {
            throw new LengthInvalidException(ErrorMessages.RACING_CAR_COUNT_MISMATCH);
        }
    }

    public RoundResultDto toDto() {
        return new RoundResultDto(carStates);
    }
}
