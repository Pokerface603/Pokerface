package pokerface.pokerface.domain.detail.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pokerface.pokerface.domain.detail.entity.Detail;

import java.util.List;

@Repository
public interface DetailRepository extends JpaRepository<Detail, Long> {
    List<Detail> findPagingByMemberId(Pageable pageable, Long memberId);

    Long countByMemberId(Long memberId);

    @Query(value = "select count(*) from Detail d where d.result='WIN' and d.member.id=:memberId ")
    Long countWinByMemberId(@Param("memberId") Long memberId);
}
