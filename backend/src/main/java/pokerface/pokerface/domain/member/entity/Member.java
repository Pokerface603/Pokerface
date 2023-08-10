package pokerface.pokerface.domain.member.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import pokerface.pokerface.global.BaseTime;
import javax.persistence.*;

import static pokerface.pokerface.global.Constants.BOUNTY_RATIO;
import static pokerface.pokerface.global.Constants.ROUND_UNIT;

@Entity
@Getter @Setter
@NoArgsConstructor
@ToString
@DynamicInsert
public class Member extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(nullable = false, length = 30, unique = true)
    private String email;

    @Column(nullable = false)
    private String userPassword;

    @Column(nullable = false, length = 30)
    private String nickname;

    @ColumnDefault("1000")
    private Integer rating;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'QUEEN'")
    private Tier tier;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    private String socialId;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("'ROLE_USER'")
    private Role userRole;

    private String authKey;

    @ColumnDefault("false")
    private Boolean emailAuth;

    @ColumnDefault("'Y'")
    private String status;

    public Member(String email, String nickname, String userPassword, Integer rating){
        this.email = email;
        this.nickname = nickname;
        this.userPassword = userPassword;
        this.rating = rating;
    }

    public Member(String email, String userPassword, String nickname, Tier tier) {
        this.email = email;
        this.userPassword = userPassword;
        this.nickname = nickname;
        this.tier = tier;
    }

    @Builder
    public Member(String email, String userPassword, String nickname, String authKey) {
        this.email = email;
        this.userPassword = userPassword;
        this.nickname = nickname;
        this.authKey = authKey;
    }

    public Long convertRatingToBounty(){
        return Math.round(Math.pow(BOUNTY_RATIO, rating)) * ROUND_UNIT;
    }
}
