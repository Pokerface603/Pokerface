package pokerface.pokerface.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pokerface.pokerface.domain.friend.entity.Friend;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findByFromEmail(String email);
}
