package racingcar.exception.domain;

import racingcar.exception.ErrorMessages;

public class CarNameLengthExceededException extends IllegalArgumentException {
    public CarNameLengthExceededException() {
        super(ErrorMessages.CAR_NAME_TOO_LONG);
    }
}
