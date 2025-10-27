package racingcar.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoundResultTest {
    @Test
    @DisplayName("정상 생성")
    public void createRoundResult() {
        assertDoesNotThrow(
                () -> new RoundResult(
                        2,
                        List.of(new CarDto("ab", 1), new CarDto("cd", 2))
                )
        );
    }

    // == 예외 상황 ==
    @Test
    @DisplayName("경주하는 차의 수와 입력된 차의 수 다르면 예외")
    public void racingCarsAndInputCarsNotEq() {
        List<CarDto> racingCars = List.of(new CarDto("ab", 1), new CarDto("cd", 2));

        assertThrows(IllegalArgumentException.class, () -> new RoundResult(1, racingCars));
        assertThrows(IllegalArgumentException.class, () -> new RoundResult(3, racingCars));
    }
}
