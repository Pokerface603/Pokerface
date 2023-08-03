package pokerface.pokerface.domain.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.domain.friend.entity.Friend;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findFriendByFromId(Long fromId);
}
