package pokerface.pokerface.domain.detail.entity;

import lombok.Getter;

@Getter
public enum Result {
    WIN("win"),
    LOSE("lose"),
    FORCE("force");

    private final String value;

    private Result(String value){
        this.value = value;
    }
}
