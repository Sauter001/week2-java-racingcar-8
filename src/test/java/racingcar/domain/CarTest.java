package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;
import static camp.nextstep.edu.missionutils.test.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racingcar.dto.CarDto;

public class CarTest {
    private final static int GO = 4;
    private final static int STOP = 3;

    @Test
    @DisplayName("정상 자동차명 생성")
    public void carNameTest() {
        // given
        String carNameStr = "pobi";
        // when
        Car car = new Car(carNameStr);
        // then
        CarDto carDto = car.toDto();

        assertEquals(carNameStr, carDto.name());
        assertEquals(0, carDto.position());
    }

    @Test
    @DisplayName("공백 제거 테스트")
    public void nameTrimTest() {
        // given
        String carNameStr = "   pobi        ";
        String trimmedCarName = carNameStr.trim();
        // when
        Car car = new Car(carNameStr);
        // then
        assertEquals(trimmedCarName, car.toDto().name());
    }

    @ParameterizedTest
    @ValueSource(strings = {"f", "go", "cat", "good", "12345"})
    @DisplayName("5자 이하 이름 생성 가능")
    public void validNameLengthTest(String carNameStr) {
        assertDoesNotThrow(() -> new Car(carNameStr));
    }

    @Test
    @DisplayName("자동차 이동 테스트")
    public void movingCarTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    // given
                    Car car = new Car("pobi");

                    // when
                    for (int i = 0; i < 5; i++) {
                        car.move();
                    }

                    // then
                    assertEquals(3, car.toDto().position());
                },
                GO, GO, STOP, GO, STOP
        );
    }

    // 예외 상황
    @Test
    @DisplayName("6자 이상 이름 생성 실패")
    public void invalidNameLengthTest() {
        assertThrows(IllegalArgumentException.class, () -> new Car("pikachu"));
    }

    @ParameterizedTest
    @DisplayName("빈 문자열 생성 실패")
    @ValueSource(strings = {"", " ", "   ", "    "})
    public void emptyNameLengthTest(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Car(name));
    }
}
