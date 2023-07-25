package pokerface.pokerface.domain.detail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.repository.DetailRepository;

@Service
@Transactional
@RequiredArgsConstructor
public class DetailService {
    private DetailRepository detailRepository;
}
