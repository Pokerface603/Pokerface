package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.detail.service.DetailService;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.dto.response.GameLogResponse;
import pokerface.pokerface.domain.history.dto.response.HistoryResponse;
import pokerface.pokerface.domain.history.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.history.entity.History;
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
    private final DetailService detailService;
    private final MemberService memberService;

    private static final Integer RATING_SCALE = 400;
    private static final Integer RATING_WEIGHT = 60;

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public History findById(Long historyId){
        return historyRepository.findById(historyId).orElseThrow(IllegalAccessError::new);
    }

    public HistoryResponse getHistory(Long historyId){
        History history = findById(historyId);

        return HistoryResponse.of(history, convertGameLogToData(history.getGameLog()));
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
        Long playerCount = detailService.countByMemberId(player.getId()) + 1;
        Long opponentCount = detailService.countByMemberId(opponent.getId()) + 1;

        return (int)Math.round(player.getRating() + (result.getValue() - expectRate) * (RATING_WEIGHT * opponentCount) / (playerCount + opponentCount));
    }

    // DB의 게임 로그를 라운드 로그로 분리하는 메소드
    public GameLogResponse convertGameLogToData(String gameLog){
        return GameLogResponse.of(Pattern.compile("#")
                .splitAsStream(gameLog)
                .map(this::convertRoundLogToData)
                .collect(Collectors.toList()));
    }

    // 분리된 라운드 로그를 라운드 게임 정보로 변환하는 메소드
    public RoundLogResponse convertRoundLogToData(String roundLog){
        StringTokenizer st = new StringTokenizer(roundLog, "$");
        return RoundLogResponse.of(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Pattern.compile(",")
                        .splitAsStream(st.nextToken())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()),
                Result.valueOf(st.nextToken()));
    }
}
