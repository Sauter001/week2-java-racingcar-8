package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RoundResultDto;
import racingcar.exception.ErrorMessages;
import racingcar.exception.common.LengthInvalidException;

import java.util.ArrayList;
import java.util.List;

public class RaceResult {
    private final List<RoundResultDto> roundResults;
    private final int numOfRounds;

    public RaceResult(NumOfRounds numOfRounds) {
        this.roundResults = new ArrayList<>();
        this.numOfRounds = numOfRounds.toInteger();
    }

    public void addRoundResult(RoundResultDto roundResult) {
        if (roundResults.size() >= numOfRounds) {
            throw new LengthInvalidException(ErrorMessages.RACE_ALREADY_FINISHED);
        }

        roundResults.add(roundResult);
    }

    public List<CarDto> findWinners() {
        if (roundResults.isEmpty()) {
            return List.of();
        }

        // 마지막 라운드의 우승자 찾기
        RoundResultDto lastRound = roundResults.getLast();
        List<CarDto> finalResults = lastRound.carStates() ;

        int maxPosition = findMaxPosition(finalResults);

        return finalResults.stream()
                .filter(carDto -> carDto.position() == maxPosition)
                .toList();
    }

    private int findMaxPosition(List<CarDto> finalResults) {
        return finalResults.stream().mapToInt(CarDto::position).max().orElse(0);
    }

    public RaceResultDto toDto() {
        List<CarDto> winners = findWinners();
        return new RaceResultDto(roundResults, winners);
    }
}
