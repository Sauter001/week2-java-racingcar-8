package racingcar.exception.domain;

public class CarNameLengthExceededException extends IllegalArgumentException {
    public CarNameLengthExceededException() {
        super("이름은 5자 이하여야 합니다.");
    }
}
