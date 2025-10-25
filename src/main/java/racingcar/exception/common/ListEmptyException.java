package racingcar.exception.common;

public class ListEmptyException extends IllegalArgumentException {
    public ListEmptyException(String listName) {
        super(String.format("List %s 가 비어있습니다.", listName));
    }
}
