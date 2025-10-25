package racingcar.view;

import racingcar.domain.Car;
import racingcar.dto.InputDto;

import java.util.List;

public interface View {
    InputDto inputRacingInfo();
    void printRacingProgress(List<Car> cars);
    void printRacingResults(List<Car> cars);
}
