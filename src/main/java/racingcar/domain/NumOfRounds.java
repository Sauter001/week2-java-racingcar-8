package racingcar.domain;

import racingcar.exception.common.NumberNotPositiveException;

public class NumOfRounds {
    private final int numOfRounds;

    public NumOfRounds(String numOfRounds) {
        numOfRounds = numOfRounds.trim();
        this.numOfRounds = convertToInteger(numOfRounds);
    }

    private int convertToInteger(String numOfRounds) {
        // NumberFormatException은 IllegalArgumentsException을 상속 받으므로 throw 생략
        int roundValue = Integer.parseInt(numOfRounds);

        if (roundValue <= 0) {
            throw new NumberNotPositiveException(roundValue);
        }

        return roundValue;
    }

    public int toInteger() {
        return numOfRounds;
    }
}
