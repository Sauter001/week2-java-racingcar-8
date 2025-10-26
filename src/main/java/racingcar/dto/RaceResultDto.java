package racingcar.dto;

import java.util.List;

public record RaceResultDto(
        List<RoundResultDto> roundResults,
        List<CarDto> winners
) {
}
