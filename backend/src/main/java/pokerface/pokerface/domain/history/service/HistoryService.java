package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.service.DetailService;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.history.repository.HistoryRepository;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final DetailService detailService;

    public List<History> findAll(){
        return historyRepository.findAll();
    }

    public History findById(Long historyId){
        return historyRepository.findById(historyId).orElseThrow(IllegalAccessError::new);
    }

    public void save(HistoryRequest historyRequest) {
        History history = historyRepository.save(historyRequest.toHistory());
        detailService.save(new DetailRequest(historyRequest.getGameLog(), 1100, "WIN"), history, historyRequest.getWinner());
        detailService.save(new DetailRequest(historyRequest.getGameLog(), 900, "LOSE"), history, historyRequest.getWinner());
    }
}
