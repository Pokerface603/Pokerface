package pokerface.pokerface.domain.detail.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.dto.response.GameLogResponse;
import pokerface.pokerface.domain.detail.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.detail.entity.Result;
import pokerface.pokerface.domain.detail.repository.DetailRepository;

import java.util.StringTokenizer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static pokerface.pokerface.domain.detail.entity.Result.*;
@Service
@Transactional
@RequiredArgsConstructor
public class DetailService {
    private DetailRepository detailRepository;

    public GameLogResponse convertGameLogtoData(String gameLog){
        return GameLogResponse.of(Pattern.compile("#")
                .splitAsStream(gameLog)
                .map(this::convertRoundLogtoData)
                .collect(Collectors.toList()));
    }

    public RoundLogResponse convertRoundLogtoData(String roundLog){
        StringTokenizer st = new StringTokenizer(roundLog, "$");
        return RoundLogResponse.of(Integer.parseInt(st.nextToken()),
                Pattern.compile(",")
                        .splitAsStream(st.nextToken())
                        .map(Integer::parseInt)
                        .collect(Collectors.toList()),
                getResult(st.nextToken()));
    }

    public Result getResult(String result){
        if(result.equals(WIN.getValue())){
            return WIN;
        }
        if(result.equals(LOSE.getValue())){
            return LOSE;
        }
        if(result.equals(FORCE.getValue())){
            return FORCE;
        }
        return null;
    }
}
