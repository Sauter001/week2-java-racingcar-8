package racingcar.exception.domain;

public class CarNameAlreadyExistsException extends IllegalArgumentException {
    public CarNameAlreadyExistsException(String duplicateName) {
        super("레이싱카 " + duplicateName + " 의 이름이 이미 존재합니다.");
    }
}
