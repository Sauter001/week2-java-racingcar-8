package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.dto.CarDto;

import java.util.List;

public class RaceResultTest {
    @Test
    @DisplayName("경기 수보다 경기 결과가 더 추가되면 예외")
    public void addResultWhenRaceFinished() {
        NumOfRounds rounds = new NumOfRounds("2");
        RaceResult raceResult = new RaceResult(rounds);
        assertThrows(IllegalArgumentException.class, () -> {
            List<CarDto> carDtos = List.of(new CarDto("abc", 1), new CarDto("def", 1));
           for (int i = 0; i < 5; i++) {
               raceResult.addRoundResult(new RoundResult(2, carDtos));
           }
        });
    }

    @Test
    @DisplayName("경기 없이 우승자 찾을 시 예외")
    public void findWinnerWithoutRound() {
        NumOfRounds rounds = new NumOfRounds("2");
        RaceResult raceResult = new RaceResult(rounds);
        assertThrows(IllegalArgumentException.class, raceResult::findWinners);
    }
}
