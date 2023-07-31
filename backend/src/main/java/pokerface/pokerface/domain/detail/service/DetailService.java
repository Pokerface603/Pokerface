package pokerface.pokerface.domain.detail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.dto.response.GameLogResponse;
import pokerface.pokerface.domain.detail.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.detail.repository.DetailRepository;
import pokerface.pokerface.domain.history.repository.HistoryRepository;
import pokerface.pokerface.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;
    private final HistoryRepository historyRepository;
    private final MemberRepository memberRepository;

    private static final Integer RATING_SCALE = 400;
    private static final Integer RATING_WEIGHT = 60;

    public List<Detail> findAll(){
        return detailRepository.findAll();
    }

    public List<Detail> findByMemberId(Long memberId) {
        return detailRepository.findDetailByMemberId(memberId);
    }

    public Detail findById(Long detailId){
        return detailRepository.findById(detailId).orElseThrow(IllegalAccessError::new);
    }

    public DetailResponse getDetail(Long detailId){
        Detail detail = findById(detailId);

        return DetailResponse.of(detail, convertGameLogtoData(detail.getGameLog()));
    }

    public void save(DetailRequest detailRequest, Long historyId, Long memberId){
        detailRepository.save(detailRequest.toDetail(
                historyRepository.findById(historyId).orElseThrow(IllegalAccessError::new),
                memberRepository.findById(memberId).orElseThrow(IllegalAccessError::new)));
    }

    // DB의 게임 로그를 라운드 로그로 분리하는 메소드
    public GameLogResponse convertGameLogtoData(String gameLog){
        return GameLogResponse.of(Pattern.compile("#")
                .splitAsStream(gameLog)
                .map(this::convertRoundLogtoData)
                .collect(Collectors.toList()));
    }

    // 분리된 라운드 로그를 라운드 게임 정보로 변환하는 메소드
    public RoundLogResponse convertRoundLogtoData(String roundLog){
        StringTokenizer st = new StringTokenizer(roundLog, "$");
        return RoundLogResponse.of(Integer.parseInt(st.nextToken()),
                Pattern.compile(",")
                        .splitAsStream(st.nextToken())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()),
                Result.valueOf(st.nextToken()));
    }

    public Integer calculateRating(Integer myRating, Integer opponentRating, Integer myPlays, Integer opponentPlays, Result result){
        double expectRate = 1 / (Math.pow(10, (double)(opponentRating - myRating)/400) + 1);

        return (int)Math.round(myRating + 60 * opponentPlays / (myPlays + opponentPlays) * (result.getValue() - expectRate));
    }
}