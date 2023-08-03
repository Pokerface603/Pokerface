package pokerface.pokerface.domain.room.repository;

import org.springframework.data.repository.CrudRepository;
import pokerface.pokerface.domain.room.entity.Room;

import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    Optional<Room> findRoomBySessionId(String sessionId);

}

