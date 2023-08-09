package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.detail.repository.DetailRepository;
import pokerface.pokerface.domain.detail.service.DetailService;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.dto.response.GameLogResponse;
import pokerface.pokerface.domain.history.dto.response.HistoryResponse;
import pokerface.pokerface.domain.history.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.history.entity.PlayerType;
import pokerface.pokerface.domain.history.repository.HistoryRepository;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final DetailRepository detailRepository;
    private final DetailService detailService;
    private final MemberService memberService;

    private static final Integer RATING_SCALE = 400;            // ELO 승리확률 가중치
    private static final Integer RATING_WEIGHT = 60;            // ELO 획득점수 가중치
    private static final Integer ROUND_UNIT = 100000;           // 현상금 반올림 단위
    private static final Double BOUNTY_RATIO = 1.0092528860;    // rating -> 현상금 변환 지수

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public History findById(Long historyId){
        return historyRepository.findById(historyId).orElseThrow(IllegalAccessError::new);
    }

    public HistoryResponse getHistory(Long historyId, Long memberId){
        History history = findById(historyId);

        return HistoryResponse.of(history, convertGameLogToData(history.getGameLog(), isHost(history, memberId)));
    }

    @Transactional
    public void save(HistoryRequest historyRequest) {
        Member host = memberService.findById(historyRequest.getHostId());
        Member guest = memberService.findById(historyRequest.getGuestId());
        History history = historyRepository.save(historyRequest.toHistory(host, guest));
        Result hostResult = Result.valueOf(historyRequest.getResult());

        detailService.save(new DetailRequest(calculateRating(host, guest, hostResult), hostResult), history, host);
        detailService.save(new DetailRequest(calculateRating(guest, host, hostResult.reverse()), hostResult.reverse()), history, guest);
    }

    public Integer calculateRating(Member player, Member opponent, Result result){
        double expectRate = 1 / (Math.pow(10, (double)(opponent.getRating() - player.getRating())/RATING_SCALE) + 1);
        Double playerCount = calculateCount(detailRepository.countByMemberId(player.getId()));
        Double opponentCount = calculateCount(detailRepository.countByMemberId(opponent.getId()));

        return (int)Math.round(player.getRating() + (result.getValue() - expectRate) * (RATING_WEIGHT * opponentCount) / (playerCount + opponentCount));
    }

    // 레이팅을 현상금으로 변환하는 메소드
    public Long convertRatingToBounty(Integer rating){
        return Math.round(Math.pow(BOUNTY_RATIO, rating) / ROUND_UNIT) * ROUND_UNIT;
    }

    // 게임 판수에 의한 가중치를 구하기 위한 메소드
    public Double calculateCount(Long memberCount){
        return (Math.log(memberCount + 1D) / Math.log(2)) + 1;
    }

    // DB의 게임 로그를 라운드 로그로 분리하는 메소드
    public GameLogResponse convertGameLogToData(String gameLog, boolean isHost){
        return GameLogResponse.of(Pattern.compile("#")
                .splitAsStream(gameLog)
                .filter(x -> !x.equals(""))
                .map(roundLog -> convertRoundLogToData(roundLog, isHost))
                .collect(Collectors.toList()));
    }

    // 분리된 라운드 로그를 라운드 게임 정보로 변환하는 메소드
    public RoundLogResponse convertRoundLogToData(String roundLog, boolean isHost){
        StringTokenizer st = new StringTokenizer(roundLog, "$");
        return RoundLogResponse.of(PlayerType.valueOf(st.nextToken().toUpperCase()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Pattern.compile(",")
                        .splitAsStream(st.nextToken())
                        .filter(x -> !x.equals(""))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()),
                Result.valueOf(st.nextToken()), isHost);
    }

    public boolean isHost(History history, Long memberId){
        return history.getHost().getId().equals(memberId);
    }
}
