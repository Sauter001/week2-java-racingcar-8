package racingcar.exception.common;

import racingcar.exception.ErrorMessages;

public class ListEmptyException extends IllegalArgumentException {
    public ListEmptyException(String listName) {
        super(String.format(ErrorMessages.LIST_EMPTY, listName));
    }
}
