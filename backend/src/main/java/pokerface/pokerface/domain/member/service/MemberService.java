package pokerface.pokerface.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pokerface.pokerface.domain.member.dto.request.MemberRequest;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(IllegalAccessError::new);
    }

    public void save(MemberRequest memberRequest) {
        memberRepository.save(memberRequest.toMember());
    }
}
