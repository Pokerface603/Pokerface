package pokerface.pokerface.domain.member.dto.response;

import lombok.Builder;
import pokerface.pokerface.domain.member.entity.Member;

@Builder
public class MemberResponse {
    private final Long memberId;

    private final String email;

    private final String nickname;

    public static MemberResponse of(Member member){
        return MemberResponse.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .build();
    }
}
