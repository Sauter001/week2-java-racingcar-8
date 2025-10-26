package racingcar.exception.domain;

import racingcar.exception.ErrorMessages;

public class CarNameAlreadyExistsException extends IllegalArgumentException {
    public CarNameAlreadyExistsException(String duplicateName) {
        super(String.format(ErrorMessages.CAR_NAME_DUPLICATE, duplicateName));
    }
}
