package pokerface.pokerface.domain.history.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.history.repository.HistoryRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class HistoryService {
    private HistoryRepository historyRepository;
}
