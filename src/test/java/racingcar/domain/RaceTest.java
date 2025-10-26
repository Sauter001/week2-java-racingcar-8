package racingcar.domain;

import camp.nextstep.edu.missionutils.test.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceTest {
    private final static int GO = 4;
    private final static int STOP = 3;

    private final Cars cars = new Cars("abc,def,ghi");
    private final Race race = new Race(cars, "3");

    // 완주는 라운드 수만큼 매번 전진한 차 (한 번도 안 멈춘 차)라고 가정

    @Test
    @DisplayName("완주자가 있으면서 우승자가 1명")
    public void singleWinnerRace() {
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 3));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = race.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, STOP, STOP,
                GO, GO, GO,
                GO, GO, STOP
        );
    }

    @Test
    @DisplayName("완주자가 있으면서 우승자가 여러 명")
    public void multipleWinnerRace() {
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 3), new CarDto("def", 3));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = race.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, GO, STOP,
                GO, GO, GO,
                GO, GO, STOP
        );
    }

    @Test
    @DisplayName("완주자가 없으면서 우승자가 1명")
    public void singleWinnerRaceWithoutFinish() {
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 2));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = race.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, STOP, STOP,
                GO, GO, STOP,
                STOP, STOP, STOP
        );
    }

    @Test
    @DisplayName("완주자가 없으면서 우승자가 여러 명")
    public void multipleWinnerRaceWithoutFinish() {
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 2), new CarDto("def", 2));


        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = race.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, STOP, STOP,
                GO, GO, STOP,
                STOP, GO, STOP
        );
    }
}
