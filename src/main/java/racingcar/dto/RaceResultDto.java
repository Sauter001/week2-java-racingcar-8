package racingcar.dto;

import java.util.List;
import java.util.function.Consumer;

public record RaceResultDto(
        List<RoundResultDto> roundResults,
        List<CarDto> winners
) {
    public void forEachRound(Consumer<RoundResultDto> consumer) {
        this.roundResults.forEach(consumer);
    }
}
