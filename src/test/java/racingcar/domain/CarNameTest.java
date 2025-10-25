package racingcar.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class CarNameTest {
    @Test
    @DisplayName("정상 자동차명 생성")
    public void carNameTest() {
        // given
        String carNameStr = "pobi";
        // when
        CarName carName = new CarName(carNameStr);
        // then
        Assertions.assertEquals(carNameStr, carName.toString());
    }

    @Test
    @DisplayName("공백 제거 테스트")
    public void nameTrimTest() {
        // given
        String carNameStr = "   pobi        ";
        String trimmedCarName = carNameStr.trim();
        // when
        CarName carName = new CarName(carNameStr);
        // then
        Assertions.assertEquals(trimmedCarName, carName.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "go", "cat", "good", "12345"})
    @DisplayName("5자 이하 이름 생성 가능")
    public void validNameLengthTest(String carNameStr) {
        // given & when
        CarName carName = new CarName(carNameStr);
        // then
        Assertions.assertEquals(carNameStr, carName.toString());
    }

    // 예외 상황
    @Test
    @DisplayName("6자 이상 이름 생성 실패")
    public void invalidNameLengthTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarName("pikachu"));
    }

    @ParameterizedTest
    @DisplayName("빈 문자열 생성 실패")
    @ValueSource(strings = {"", " ", "   ", "    "})
    public void emptyNameLengthTest(String name) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new CarName(name));
    }
}
