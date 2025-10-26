package racingcar.domain;

import racingcar.exception.common.NumberNotPositiveException;
import racingcar.parser.RoundInputParser;

public class NumOfRounds {
    private final int numOfRounds;

    public NumOfRounds(String roundInput) {
        RoundInputParser inputParser = new RoundInputParser();
        this.numOfRounds = inputParser.parse(roundInput);
    }

    public int toInteger() {
        return numOfRounds;
    }
}
