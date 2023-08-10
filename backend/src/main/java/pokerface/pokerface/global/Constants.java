package pokerface.pokerface.global;

public final class Constants {
    private Constants(){}

    // ELO 승리확률 가중치
    public static final Integer RATING_SCALE = 400;

    // ELO 획득점수 가중치
    public static final Integer RATING_WEIGHT = 60;

    // 현상금 반올림 단위
    public static final Integer ROUND_UNIT = 10000;

    // rating -> 현상금 변환 지수
    public static final Double BOUNTY_RATIO = 1.0092528860;
}
