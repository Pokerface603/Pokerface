package pokerface.pokerface.domain.detail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.dto.response.DetailCountResponse;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.entity.Detail;
import pokerface.pokerface.domain.detail.repository.DetailRepository;
import pokerface.pokerface.domain.history.entity.History;
import pokerface.pokerface.domain.member.entity.Member;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DetailService {
    private final DetailRepository detailRepository;

    public List<DetailResponse> findAll(){
        return detailRepository.findAll().stream()
                .map(DetailResponse::of)
                .collect(Collectors.toList());
    }

    public List<DetailResponse> findPagingByMemberEmail(Pageable pageable, String email) {
        return detailRepository.findPagingByMemberEmail(pageable, email).stream()
                .map(DetailResponse::of)
                .collect(Collectors.toList());
    }

    public Detail findById(Long detailId){
        return detailRepository.findById(detailId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public DetailResponse getDetail(Long detailId){
        return DetailResponse.of(findById(detailId));
    }

    @Transactional
    public void save(DetailRequest detailRequest, History history, Member member){
        detailRepository.save(detailRequest.toDetail(history, member));
    }

    public DetailCountResponse countByMemberEmail(String email){
        return DetailCountResponse.of(detailRepository.countByMemberEmail(email), detailRepository.countWinByMemberEmail(email));
    }
}