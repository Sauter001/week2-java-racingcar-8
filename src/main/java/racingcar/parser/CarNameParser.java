package racingcar.parser;

import racingcar.exception.common.ListEmptyException;
import racingcar.exception.domain.CarNameEmptyException;

import java.util.Arrays;
import java.util.List;

public class CarNameParser {
    private static final String DELIMITER = ",";

    public List<String> parseCars(String carsInput) {
        validateCarsFormat(carsInput);

        return Arrays.stream(carsInput.split(DELIMITER))
                .map(String::trim)
                .toList();
    }

    private void validateCarsFormat(String carsInput) {
        // 자동차 입력이 ,로 끝나면 예외 처리
        if (carsInput.endsWith(",")) {
            throw new CarNameEmptyException();
        }
    }
}
