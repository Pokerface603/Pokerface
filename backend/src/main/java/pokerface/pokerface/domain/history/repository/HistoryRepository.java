package pokerface.pokerface.domain.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pokerface.pokerface.domain.history.entity.History;

import java.util.List;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    @Query("select h from History h where h.host.email=:email or h.guest.email=:email order by h.createdAt desc")
    List<History> findByHostEmailOrGuestEmailOrderByCreatedAtDesc(@Param("email") String email);
}
