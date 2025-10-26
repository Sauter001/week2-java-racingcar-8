package racingcar.exception.common;

import racingcar.exception.ErrorMessages;

public class NumberNotPositiveException extends IllegalArgumentException {
    public NumberNotPositiveException(int number) {
        super(String.format(ErrorMessages.NUMBER_NOT_POSITIVE, number));
    }
}
