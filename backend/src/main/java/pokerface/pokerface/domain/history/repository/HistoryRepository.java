package pokerface.pokerface.domain.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.history.entity.History;

@Repository
@Transactional(readOnly = true)
public interface HistoryRepository extends JpaRepository<History, Long> {
}
