package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
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

    // == 예외 사항 ==
    @Test
    void carNameLengthGreaterThan5() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("pobi,javaji", "1"))
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
