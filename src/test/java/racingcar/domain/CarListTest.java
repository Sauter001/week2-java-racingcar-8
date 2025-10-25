package racingcar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarListTest {

    // 예외 테스트
    @Test
    @DisplayName("입력된 차가 없음")
    void carDoesntExist() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarList(""));
    }

    @Test
    @DisplayName("중복되는 차 존재")
    void carNameDuplicationTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarList("pobi,pobi,jun"));
    }

    @Test
    @DisplayName("차 이름 길이가 5 초과")
    void carNameLengthGreaterThan5() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarList("pobi,california,jun"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,,jun", "pobi, ,jun", ",pobi,jun", "pobi,jun,", "pobi,jun, "})
    @DisplayName("쉼표 사이의 빈 문자열")
    void blankBetweenCommas(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarList(input));
    }
}
