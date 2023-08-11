package pokerface.pokerface.domain.history.entity;

import lombok.Getter;

@Getter
public enum PlayerType {
    HOST(true),
    GUEST(false);

    private final boolean value;

    PlayerType(boolean value){
        this.value = value;
    }
}
