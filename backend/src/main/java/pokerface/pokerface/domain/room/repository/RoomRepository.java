package pokerface.pokerface.domain.room.repository;

import org.springframework.data.repository.CrudRepository;
import pokerface.pokerface.domain.room.entity.Room;

public interface RoomRepository extends CrudRepository<Room, String> {

}

