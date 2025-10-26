package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.dto.CarDto;
import racingcar.dto.InputDto;
import racingcar.dto.RaceResultDto;
import racingcar.dto.RoundResultDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ConsoleView implements View {
    private static final String CARS_INPUT_PROMPT = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)";
    private static final String ROUND_INPUT_PROMPT = "시도할 횟수는 몇 회인가요?";
    private static final String RACING_CAR_DISTANCE_FORMAT = "%s : %s\n";

    @Override
    public InputDto inputRacingInfo() {
        System.out.println(CARS_INPUT_PROMPT);
        String racingCars = readInputSafely();
        System.out.println(ROUND_INPUT_PROMPT);
        String round = readInputSafely();
        return new InputDto(racingCars, round);
    }

    @Override
    public void printRacingResult(RaceResultDto raceResultDto) {
        List<CarDto> winners = raceResultDto.winners();
        List<RoundResultDto> roundResults = raceResultDto.roundResults();

        System.out.println();
        System.out.println("실행 결과");
        for (RoundResultDto roundResultDto : roundResults) {
            printRacingProgress(roundResultDto.carStates());
            System.out.println();
        }

        printWinners(winners);
    }

    private void printWinners(List<CarDto> winners) {
        List<String> carNames = winners.stream().map(CarDto::name).toList();
        System.out.printf("최종 우승자 : %s\n", String.join(", ", carNames));
    }

    private void printRacingProgress(List<CarDto> carDtos) {
        for (CarDto carDto : carDtos) {
            printCarDistance(carDto);
        }
    }


    private String readInputSafely() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private void printCarDistance(CarDto carDto) {
        String distance = "-".repeat(carDto.position());
        System.out.printf(RACING_CAR_DISTANCE_FORMAT, carDto.name(), distance);
    }
}
