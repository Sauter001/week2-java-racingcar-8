package racingcar.exception.domain;

public class CarNameEmptyException extends IllegalArgumentException {
    public CarNameEmptyException() {
        super("이름은 공백일 수 없습니다.");
    }
}
