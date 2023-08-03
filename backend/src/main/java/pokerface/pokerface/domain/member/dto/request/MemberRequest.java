package pokerface.pokerface.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pokerface.pokerface.domain.member.entity.Member;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String email;

    private String nickname;

    private String userPassword;

    private Integer rating;

    public Member toMember(){
        return new Member(email, nickname, userPassword, rating);
    }
}
