package racingcar.domain;

import camp.nextstep.edu.missionutils.test.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RaceTest {
    private final static int GO = 4;
    private final static int STOP = 3;

    private final Cars defaultCars = new Cars("abc,def,ghi");
    private final Race defaultRace = new Race(defaultCars, "3");

    // 완주는 라운드 수만큼 매번 전진한 차 (한 번도 안 멈춘 차)라고 가정

    @Test
    @DisplayName("완주자가 있으면서 우승자가 1명")
    public void singleWinnerRace() {
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 3));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = defaultRace.start();
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
                    RaceResult result = defaultRace.start();
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
                    RaceResult result = defaultRace.start();
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
                    RaceResult result = defaultRace.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, STOP, STOP,
                GO, GO, STOP,
                STOP, GO, STOP
        );
    }

    @Test
    @DisplayName("차가 움직이지 않을 때 공동 우승")
    public void carsStoppedRace() {
        List<CarDto> expectedWinners = List.of(
                new CarDto("abc", 0),
                new CarDto("def", 0),
                new CarDto("ghi", 0));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = defaultRace.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                STOP, STOP, STOP,
                STOP, STOP, STOP,
                STOP, STOP, STOP
        );
    }

    @Test
    @DisplayName("차가 매번 움직일 때 공동 우승")
    public void carsAlwaysMovedRace() {
        List<CarDto> expectedWinners = List.of(
                new CarDto("abc", 3),
                new CarDto("def", 3),
                new CarDto("ghi", 3));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = defaultRace.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, GO, GO,
                GO, GO, GO,
                GO, GO, GO
        );
    }

    @Test
    @DisplayName("자동차 1개 뿐일 때")
    public void onlyOneCarRace() {
        Cars onlyOneCar = new Cars("abc");
        Race onePlayerRace = new Race(onlyOneCar, "3");
        List<CarDto> expectedWinners = List.of(new CarDto("abc", 3));

        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    RaceResult result = onePlayerRace.start();
                    RaceResultDto resultDto = result.toDto();
                    assertEquals(expectedWinners, resultDto.winners());
                },
                GO, GO, GO
        );
    }

    // 예외 상황
    @Test
    @DisplayName("0 라운드면 실패")
    public void zeroRoundRace() {
        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    assertThrows(IllegalArgumentException.class, () -> new Race(defaultCars, "0"));
                }, GO
        );
    }

    @Test
    @DisplayName("음수 라운드면 실패")
    public void negativeRoundRace() {
        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    assertThrows(IllegalArgumentException.class, () -> new Race(defaultCars, "-1"));
                }, GO
        );
    }

    @Test
    @DisplayName("수가 아니면 실패")
    public void nonNumberRoundRace() {
        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    assertThrows(IllegalArgumentException.class, () -> new Race(defaultCars, "abc"));
                }, GO
        );
    }

    @Test
    @DisplayName("참가자가 없을 시 실패")
    public void noPlayerRace() {
        Assertions.assertRandomNumberInRangeTest(
                () -> {
                    assertThrows(IllegalArgumentException.class, () -> {
                        Cars noCars = new Cars("");
                        new Race(noCars, "1");
                    });
                },
                GO
        );
    }
}
