package pokerface.pokerface.domain.detail.entity;

import lombok.Getter;

@Getter
public enum Result {
    WIN(1),
    LOSE(0),
    DRAW(-1);

    private final Integer value;

    Result(Integer value){
        this.value = value;
    }

    public Result reverse(){
        if(this.equals(DRAW)){
            return DRAW;
        }
        if(this.equals(WIN)){
            return LOSE;
        }
        return WIN;
    }
}
