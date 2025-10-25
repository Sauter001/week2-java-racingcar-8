package racingcar.view;

import camp.nextstep.edu.missionutils.Console;
import racingcar.domain.Car;
import racingcar.dto.CarDto;
import racingcar.dto.InputDto;

import java.util.List;
import java.util.NoSuchElementException;

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
    public void printRacingProgress(List<Car> cars) {
        // for TDD
    }

    private String readInputSafely() {
        try {
            return Console.readLine();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private void printCarDistance(Car car) {
        CarDto carDto = car.toDto();
        String distance = "-".repeat(carDto.position());
        System.out.printf(RACING_CAR_DISTANCE_FORMAT, carDto.name(), distance);
    }

    @Override
    public void printRacingResults(List<Car> cars) {
        // for TDD
    }
}
