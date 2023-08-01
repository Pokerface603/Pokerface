package pokerface.pokerface.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import pokerface.pokerface.domain.detail.dto.response.GameLogResponse;
import pokerface.pokerface.domain.detail.dto.response.RoundLogResponse;
import pokerface.pokerface.domain.detail.service.DetailService;
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DetailServiceTest {

    @Test
    public void convert() throws Exception{
        String gameLog = "13$3,0,1,4,2$win" +
                "#5$1,2,3,4$win" +
                "#3$3,0,1,4,2$lose";

        DetailService detailService = new DetailService();
        GameLogResponse gameLogResponse = detailService.convertGameLogtoData(gameLog);

        for(RoundLogResponse roundLogResponse : gameLogResponse.roundLogResponses){
            System.out.println(roundLogResponse.cardNum);
            System.out.println(roundLogResponse.result);

            for (Integer now : roundLogResponse.battingMoney){
                System.out.print(now + ", ");
            }
            System.out.println();
        }
    }
}
