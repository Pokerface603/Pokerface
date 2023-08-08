package pokerface.pokerface.domain.member.dto.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberJoinReq {
    private String email;
    private String nickname;
    private String password;
}
