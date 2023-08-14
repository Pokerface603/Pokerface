package pokerface.pokerface.domain.member.entity;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum Tier {
    JACK(0, 0, 900),
    QUEEN(1, 900, 1100),
    KING(2, 1100, 1250),
    ACE(3, 1250, 1400),
    JOKER(4, 1400, 10000);

    private final Integer index;
    private final Integer lowRating;
    private final Integer highRating;

    Tier(Integer index, Integer lowRating, Integer highRating){
        this.index = index;
        this.lowRating = lowRating;
        this.highRating = highRating;
    }

    public Integer index(){
        return index;
    }

    public Tier changedTier(Integer postRating){
        if(this.lowRating > postRating){
            return valueOfIndex(this.index - 1);
        }
        if(this.highRating <= postRating){
            return valueOfIndex(this.index + 1);
        }
        return this;
    }

    private static final Map<Integer, Tier> BY_INDEX =
            Stream.of(values()).collect(Collectors.toMap(Tier::index, Function.identity()));

    public static Tier valueOfIndex(Integer index) {
        return BY_INDEX.get(index);
    }
}
