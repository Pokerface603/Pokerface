package pokerface.pokerface.domain.detail.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.detail.entity.Detail;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findDetailByMemberId(Long memberId);
}
