package racingcar.domain;

import racingcar.dto.CarDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RoundResultDto;
import racingcar.exception.ErrorMessages;
import racingcar.exception.common.LengthInvalidException;
import racingcar.exception.common.ListEmptyException;

import java.util.ArrayList;
import java.util.List;

/**
 * 경주 결과를 READ 및 INSERT 하기 위한 쿼리 객체
 * Race 실행 중 결과를 수집하고, 완료 후 결과 조회 기능 제공
 */
public class RaceResult {
    public static final String ROUND_RESULTS_LIST_NAME = "round results";
    private final List<RoundResult> roundResults;
    private final int numOfRounds;

    public RaceResult(NumOfRounds numOfRounds) {
        this.roundResults = new ArrayList<>();
        this.numOfRounds = numOfRounds.toInteger();
    }

    public void addRoundResult(RoundResult roundResult) {
        if (roundResults.size() >= numOfRounds) {
            throw new LengthInvalidException(ErrorMessages.RACE_ALREADY_FINISHED);
        }

        roundResults.add(roundResult);
    }

    public List<CarDto> findWinners() {
        if (roundResults.isEmpty()) {
            throw new ListEmptyException(ROUND_RESULTS_LIST_NAME);
        }

        // 마지막 라운드의 우승자 찾기
        RoundResult lastRound = roundResults.getLast();
        return lastRound.findCarsOnMaxPosition();
    }

    public RaceResultDto toDto() {
        List<RoundResultDto> roundResultDtos = roundResults.stream()
                .map(RoundResult::toDto)
                .toList();
        List<CarDto> winners = findWinners();
        return new RaceResultDto(roundResultDtos, winners);
    }
}
