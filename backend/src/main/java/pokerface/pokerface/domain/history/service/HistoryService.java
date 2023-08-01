package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.service.DetailService;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.history.repository.HistoryRepository;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final DetailService detailService;
    private final MemberService memberService;

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public History findById(Long historyId){
        return historyRepository.findById(historyId).orElseThrow(IllegalAccessError::new);
    }

    public void save(HistoryRequest historyRequest) {
        History history = historyRepository.save(historyRequest.toHistory());
        Member winner = memberService.findById(historyRequest.getWinner());
        Member loser = memberService.findById(historyRequest.getLoser());

        detailService.save(new DetailRequest(historyRequest.getGameLog(), calculateRating(winner, loser, 1), "WIN"), history, winner);
        detailService.save(new DetailRequest(historyRequest.getGameLog(), calculateRating(loser, winner, 0), "LOSE"), history, loser);
    }

    public Integer calculateRating(Member player, Member opponent, Integer K){
        double expectRate = 1 / (Math.pow(10, (double)(opponent.getRating() - player.getRating())/400) + 1);

        return (int)Math.round(player.getRating() + 60 * detailService.countByMemberId(opponent.getId()) / (detailService.countByMemberId(player.getId()) + detailService.countByMemberId(opponent.getId())) * (K - expectRate));
    }
}
