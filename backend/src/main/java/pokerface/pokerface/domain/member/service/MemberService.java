package pokerface.pokerface.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.member.dto.request.MemberJoinReq;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> findAll(){
        return memberRepository.findAll();
    }

    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public Member findByEmail(String email) {
        return memberRepository.findByEmail(email).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public Optional<Member> findByEmailPrincipalDetail(String email) {
        return memberRepository.findByEmail(email);
    }

    public boolean emailCheck(String email) {
        return memberRepository.existsByEmail(email);
    }

    public boolean nicknameCheck(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    public void join(MemberJoinReq memberJoinReq) {
        memberRepository.save(Member.builder()
                .email(memberJoinReq.getEmail())
                .nickname(memberJoinReq.getNickname())
                .userPassword(memberJoinReq.getPassword())
                .build());
    }

    public Optional<Member> findByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken);
    }
}
