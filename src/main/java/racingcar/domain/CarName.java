package racingcar.domain;

import racingcar.exception.domain.CarNameEmptyException;
import racingcar.exception.domain.CarNameLengthExceededException;

import java.util.Objects;

public class CarName {
    private static final int CAR_NAME_MAX_LENGTH = 5;
    private final String name;
    public CarName(String name) {
        String trimmedName = name.trim();
        validate(trimmedName);
        this.name = trimmedName;
    }

    private void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new CarNameEmptyException();
        }

        if (name.length() > CAR_NAME_MAX_LENGTH) {
            throw new CarNameLengthExceededException();
        }
    }

    @Override
    public String toString() {
        return this.name;
    }
}
