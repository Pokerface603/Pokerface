package pokerface.pokerface.domain.room.repository;

import org.springframework.data.repository.CrudRepository;
import pokerface.pokerface.domain.history.entity.GameMode;
import pokerface.pokerface.domain.room.entity.Room;

import java.util.List;

public interface RoomRepository extends CrudRepository<Room, String> {
    Room findByTitle(String title);
    List<Room> findAllByGameModeOrderByCreatedAtDesc(GameMode gameMode);
    List<Room> findAllByOrderByCreatedAtDesc();
}

