package pokerface.pokerface.domain.member.dto.request;

import pokerface.pokerface.domain.member.entity.Member;

public class MemberRequest {
    String email;

    String nickname;

    String userPassword;

    public Member toMember(){
        return new Member(email, nickname, userPassword);
    }
}
