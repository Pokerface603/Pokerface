package pokerface.pokerface.domain.detail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.repository.DetailRepository;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;

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
        return DetailResponse.of(findById(detailId));
    }

    @Transactional
    public void save(DetailRequest detailRequest, History history, Member member){
        detailRepository.save(detailRequest.toDetail(history, member));
    }

    public Long countByMemberId(Long memberId){
        return detailRepository.countByMemberId(memberId);
    }

    public Long countWinByMemberId(Long memberId){
        return detailRepository.countWinByMemberId(memberId);
    }
}