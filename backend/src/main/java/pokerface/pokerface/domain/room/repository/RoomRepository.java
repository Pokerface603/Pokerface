package pokerface.pokerface.domain.room.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pokerface.pokerface.domain.room.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}

