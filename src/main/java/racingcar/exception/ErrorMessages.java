package racingcar.exception;

public class ErrorMessages {
    // === 입력 검증 관련 ===
    public static final String CAR_NAME_EMPTY = "자동차 이름은 공백일 수 없습니다.";
    public static final String CAR_NAME_TOO_LONG = "자동차 이름은 5자 이하여야 합니다.";
    public static final String CAR_NAME_DUPLICATE = "자동차 이름 '%s'이(가) 중복되었습니다.";

    public static final String ROUND_NOT_POSITIVE = "시도 횟수는 양수여야 합니다. 입력값: %d";
    public static final String ROUND_NOT_NUMBER = "시도 횟수는 숫자여야 합니다.";
    public static final String ROUND_OUT_OF_RANGE = "시도 횟수가 허용 범위를 벗어났습니다.";

    public static final String CARS_EMPTY = "경주할 자동차가 없습니다.";

    // === 경주 진행 관련 ===
    public static final String RACING_CAR_COUNT_MISMATCH = "라운드에 참가한 자동차 수가 일치하지 않습니다. 예상: %d, 실제: %d";
    public static final String RACE_ALREADY_FINISHED = "경주가 이미 종료되어 결과를 추가할 수 없습니다.";

    // === 일반 검증 관련 ===
    public static final String NUMBER_NOT_POSITIVE = "양수가 아닌 값입니다: %d";
    public static final String LIST_EMPTY = "%s이(가) 비어있습니다.";
}
