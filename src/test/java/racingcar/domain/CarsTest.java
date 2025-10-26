package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarsTest {

    // 예외 테스트
    @Test
    @DisplayName("입력된 차가 없음")
    void carDoesntExist() {
        assertThrows(IllegalArgumentException.class, () -> new Cars(""));
    }

    @Test
    @DisplayName("중복되는 차 존재")
    void carNameDuplicationTest() {
        assertThrows(IllegalArgumentException.class, () -> new Cars("pobi,pobi,jun"));
    }

    @Test
    @DisplayName("차 이름 길이가 5 초과")
    void carNameLengthGreaterThan5() {
        assertThrows(IllegalArgumentException.class, () -> new Cars("pobi,california,jun"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"pobi,,jun", "pobi, ,jun", ",pobi,jun", "pobi,jun,", "pobi,jun, "})
    @DisplayName("쉼표 사이의 빈 문자열")
    void blankBetweenCommas(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Cars(input));
    }
}
