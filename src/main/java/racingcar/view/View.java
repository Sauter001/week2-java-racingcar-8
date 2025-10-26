package racingcar.view;

import racingcar.dto.InputDto;
import racingcar.dto.RaceResultDto;

public interface View {
    InputDto inputRacingInfo();
    void printRacingResult(RaceResultDto raceResultDto);
}
