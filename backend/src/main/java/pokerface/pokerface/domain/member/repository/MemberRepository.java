package pokerface.pokerface.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.member.entity.Member;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {
}