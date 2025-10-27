package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final String CAR_MOVE_FORMAT = "%s : %s";
    private static final String WINNER_FORMAT = "최종 우승자 : %s";

    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void normalTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @ParameterizedTest
    @DisplayName("차량 리스트 입력 공백 포함")
    @ValueSource(strings = {"pobi, woni", "pobi,   woni", "pobi, woni ", " pobi, woni", "   pobi, woni  "})
    void carsInputIncludeBlank() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi, woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("차 1대")
    void oneCarTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "2");
                    assertThat(output()).contains("pobi : -", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    @DisplayName("차가 움직이지 않을 때")
    void carsDontMoveTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    assertThat(output()).contains("최종 우승자 : pobi, woni");
                },
                STOP, STOP, STOP, STOP, STOP, STOP
        );
    }

    @Test
    @DisplayName("차가 계속 움직일 때")
    void carsKeepMovingTest() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "3");
                    assertThat(output()).contains(
                            String.format(CAR_MOVE_FORMAT, "pobi", "---"),
                            String.format(CAR_MOVE_FORMAT, "woni", "---"),
                            String.format(WINNER_FORMAT, "pobi, woni")
                    );
                },
                MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD,
                MOVING_FORWARD, MOVING_FORWARD
        );
    }

    // == 예외 사항 ==
    @Test
    @DisplayName("차이름 길이 6 이상이면 실패")
    void carNameLengthGreaterThan5() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {",pobi", "pobi,woni,", "pobi,,woni", "pobi,  ,woni"})
    @DisplayName("차 리스트에 빈 문자열이 있으면 실패")
    void carNameLengthGreaterThan5(String carsInput) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException(carsInput, "1"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "-123", "abc", "asddf"})
    @DisplayName("유효하지 않은 라운드 수")
    void invalidRoundInput(String round) {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,woni,jun", round))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @ParameterizedTest
    @DisplayName("빈 문자열 입력 테스트")
    @MethodSource("emptyStringIncludedParameters")
    void inputEmpty(String cars, String round) {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException(cars, round));
        });
    }

    private static Stream<Arguments> emptyStringIncludedParameters() {
        return Stream.of(
                Arguments.of("1", ""),
                Arguments.of("", "1"),
                Arguments.of("", "")
        );
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
