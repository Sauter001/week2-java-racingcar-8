package racingcar.parser;

import racingcar.exception.common.NumberNotPositiveException;

public class RoundInputParser {
    public int parse(String input) {
        String trimmedInput = input.trim();
        int roundValue = Integer.parseInt(trimmedInput);

        if (roundValue <= 0) {
            throw new NumberNotPositiveException(roundValue);
        }

        return roundValue;
    }
}
