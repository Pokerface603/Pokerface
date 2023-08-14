package pokerface.pokerface.domain.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.config.oauth.dto.SocialJoinDto;
import pokerface.pokerface.domain.member.dto.request.MemberJoinReq;
import pokerface.pokerface.domain.member.dto.response.MemberLoginRes;
import pokerface.pokerface.domain.member.dto.response.RankingRes;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.domain.member.entity.SocialType;
import pokerface.pokerface.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
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
        memberRepository.save(Member.join()
                .email(memberJoinReq.getEmail())
                .nickname(memberJoinReq.getNickname())
                .userPassword(memberJoinReq.getPassword())
                .authKey(memberJoinReq.getAuthKey())
                .build());
    }

    public void updateEmailAuth(String email, String authKey) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));

        if (!member.getAuthKey().equals(authKey)) { // authKey 불일치
            throw new RestException(ErrorCode.INVALID_AUTHKEY);
        }

        member.setEmailAuth(true);
    }

    public Optional<Member> findByRefreshToken(String refreshToken) {
        return memberRepository.findByRefreshToken(refreshToken);
    }

    public void updateRefreshToken(String email, String refreshToken) {
        memberRepository.findByEmail(email).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND))
                .setRefreshToken(refreshToken);
        memberRepository.flush();
    }

    public List<RankingRes> findAllRankings(){
        return findAll().stream()
                .map(RankingRes::of)
                .collect(Collectors.toList());
    }

    public List<RankingRes> findTop5Rankings(){
        return memberRepository.findTop5ByOrderByRatingDesc().stream()
                .map(RankingRes::of)
                .collect(Collectors.toList());
    }

    public RankingRes findRankingByEmail(String email){
        return RankingRes.of(findByEmail(email));
    }

    public List<MemberLoginRes> findByEmails(List<String> emails){
        return emails.stream().distinct()
                .map(memberRepository::findByEmail)
                .map(member -> new MemberLoginRes(member.get().getNickname(), member.get().getEmail()))
                .collect(Collectors.toList());
    }

    public void updateRating(String email, Integer rating) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));

        member.setRating(rating);
        member.setTier(member.getTier().changedTier(rating));
    }

    public Optional<Member> findBySocialIdAndSocialType(String socialId, SocialType socialType) {
        return memberRepository.findBySocialIdAndSocialType(socialId, socialType);
    }

    public String socialJoin(SocialJoinDto socialJoinDto) {
        memberRepository.saveAndFlush(Member.socialJoin(socialJoinDto));
        return socialJoinDto.getEmail();
    }
}
