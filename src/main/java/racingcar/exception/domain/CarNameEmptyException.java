package racingcar.exception.domain;

import racingcar.exception.ErrorMessages;

public class CarNameEmptyException extends IllegalArgumentException {
    public CarNameEmptyException() {
        super(ErrorMessages.CAR_NAME_EMPTY);
    }
}
