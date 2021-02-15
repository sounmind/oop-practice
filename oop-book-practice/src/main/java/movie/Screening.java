package movie;

import java.time.LocalDateTime;
import money.Money;

public class Screening {
    // 사용자들이 예매하는 대상인 '상영'
    private Movie movie;
    private int sequence;
    private LocalDateTime whenScreened;

    public Screening(Movie movie, int sequence, LocalDateTime whenScreened) {
        this.movie = movie; // 상영할 영화
        this.sequence = sequence; // 순번
        this.whenScreened = whenScreened; // 상영 시작 시간
    }

    public LocalDateTime getStartTime() { // 상영 시작 시간 반환
        return whenScreened;
    }

    public boolean isSequence(int sequence) { // 순번의 일치 여부 검사
        return this.sequence == sequence;
    }

    public Money getMovieFee() { // 기본 요금을 반환
        return movie.getFee();
    }

    public Reservation reserve(Customer customer, int audienceCount) {
        // 영화를 예매한 후 예매 정보를 담고 있는 Reservation의 인스턴스를 생성해 반환한다.
        return new Reservation(customer, this, calculateFee(audienceCount),
                audienceCount);
    }

    private Money calculateFee(int audienceCount) {
        // 전체 요금을 계산하기 위해 movie의 요금을 계산해 인원 수 만큼 곱한다.
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
