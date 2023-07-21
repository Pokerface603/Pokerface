package pokerface.pokerface.domain.friend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pokerface.pokerface.domain.member.entity.Member;
import pokerface.pokerface.global.BaseTime;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Friend extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_id")
    private Member from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_id")
    private Member to;
}
