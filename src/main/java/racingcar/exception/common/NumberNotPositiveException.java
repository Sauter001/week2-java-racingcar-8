package racingcar.exception.common;

public class NumberNotPositiveException extends IllegalArgumentException {
    public NumberNotPositiveException(int number) {
        super(String.format("수 %d는 양수가 아닙니다.", number));
    }
}
