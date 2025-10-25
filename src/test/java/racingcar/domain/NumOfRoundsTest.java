package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumOfRoundsTest {
    @Test
    @DisplayName("정상 객체 생성")
    void numOfRounds() {
        NumOfRounds numOfRounds = new NumOfRounds("12");
        assertEquals(12, numOfRounds.toInteger());
    }

    // 예외 상황
    @ParameterizedTest
    @ValueSource(strings = {"abc", "ddd123", "12e"})
    @DisplayName("숫자가 아닌 입력 예외")
    void nonNumberTest(String nonNumber) {
        assertThrows(IllegalArgumentException.class, () -> new NumOfRounds(nonNumber));
    }

    @Test
    @DisplayName("소수 입력 예외")
    void decimalTest() {
        assertThrows(IllegalArgumentException.class, () -> new NumOfRounds("1.23"));
    }

    @Test
    @DisplayName("INT 범위 초과")
    void integerOutOfRangeTest() {
        assertThrows(IllegalArgumentException.class, () -> new NumOfRounds("23000000000"));
    }
}
