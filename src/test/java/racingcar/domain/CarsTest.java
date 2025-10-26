package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.CarDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CarsTest {
    // 정상 테스트
    @Test
    @DisplayName("공백 포함된 입력")
    void carNamesIncludingBlank() {
        Cars cars = new Cars("pobi, woni,  jun");
        List<CarDto> dtos = List.of(new CarDto("pobi", 0), new CarDto("woni", 0), new CarDto("jun", 0));
        assertEquals(cars.getRaceResult(), dtos);
    }

    @Test
    @DisplayName("5글자 차 이름 입력")
    void carNamesExactly5() {
        Cars cars = new Cars("apple,grape,abcde");
        List<CarDto> dtos = List.of(new CarDto("apple", 0), new CarDto("grape", 0), new CarDto("abcde", 0));
        assertEquals(cars.getRaceResult(), dtos);
    }

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
    @ValueSource(strings = {"pobi,,jun", "pobi, ,jun", ",pobi,jun", "pobi,jun,", "pobi,jun, ", ","})
    @DisplayName("쉼표 사이의 빈 문자열")
    void blankBetweenCommas(String input) {
        assertThrows(IllegalArgumentException.class, () -> new Cars(input));
    }
}
