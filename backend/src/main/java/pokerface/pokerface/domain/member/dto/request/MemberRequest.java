package pokerface.pokerface.domain.member.dto.request;

import com.sun.istack.NotNull;
import lombok.Builder;
import pokerface.pokerface.domain.member.entity.Member;

@Builder
public class MemberRequest {
    @NotNull
    String email;

    @NotNull
    String nickname;

    @NotNull
    String userPassword;

    public Member toMember(){
        return new Member(email, nickname, userPassword);
    }
}
