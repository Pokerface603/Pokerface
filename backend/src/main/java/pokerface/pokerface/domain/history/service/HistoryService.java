package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.detail.repository.DetailRepository;
import pokerface.pokerface.domain.detail.service.DetailService;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.dto.response.GameLogResponse;
import pokerface.pokerface.domain.history.dto.response.HistoryResponse;
import pokerface.pokerface.domain.history.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.history.dto.response.TurnLogResponse;
import pokerface.pokerface.domain.history.entity.BetType;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.history.entity.PlayerType;
import pokerface.pokerface.domain.history.repository.HistoryRepository;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pokerface.pokerface.global.Constants.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final DetailRepository detailRepository;
    private final DetailService detailService;
    private final MemberService memberService;

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public History findById(Long historyId){
        return historyRepository.findById(historyId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public HistoryResponse getHistory(Long historyId, String email){
        History history = findById(historyId);

        return HistoryResponse.of(history, convertGameLogToData(history.getGameLog(), isHost(history, email)), isHost(history, email));
    }

    @Transactional
    public void save(HistoryRequest historyRequest) {
        Member host = memberService.findByEmail(historyRequest.getHostEmail());
        Member guest = memberService.findByEmail(historyRequest.getGuestEmail());
        History history = historyRepository.save(historyRequest.toHistory(host, guest));
        Result hostResult = Result.valueOf(historyRequest.getResult());
        Integer hostPostRating = calculateRating(host, guest, hostResult);
        Integer guestPostRating = calculateRating(guest, host, hostResult.reverse());

        detailService.save(history, host, hostPostRating, hostResult);
        detailService.save(history, guest, guestPostRating, hostResult.reverse());

        memberService.updateRating(host.getEmail(), hostPostRating);
        memberService.updateRating(guest.getEmail(), guestPostRating);
    }

    public Integer calculateRating(Member player, Member opponent, Result result){
        double expectRate = 1 / (Math.pow(10, (double)(opponent.getRating() - player.getRating())/RATING_SCALE) + 1);
        Double playerCount = calculateCount(detailRepository.countByMemberEmail(player.getEmail()));
        Double opponentCount = calculateCount(detailRepository.countByMemberEmail(opponent.getEmail()));

        return (int)Math.round(player.getRating() + (result.getValue() - expectRate) * (RATING_WEIGHT * opponentCount) / (playerCount + opponentCount));
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
                        .map(this::convertTurnLogToData)
                        .collect(Collectors.toList()),
                Result.valueOf(st.nextToken()), isHost);
    }

    public TurnLogResponse convertTurnLogToData(String turnLog){
        StringTokenizer st = new StringTokenizer(turnLog, "-");
        return TurnLogResponse.of(BetType.valueOf(st.nextToken().toUpperCase()), Integer.parseInt(st.nextToken()));
    }

    public boolean isHost(History history, String email){
        return history.getHost().getEmail().equals(email);
    }
}
