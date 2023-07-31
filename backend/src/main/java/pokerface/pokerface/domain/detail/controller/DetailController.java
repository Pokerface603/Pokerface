package pokerface.pokerface.domain.detail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.dto.response.ExpectRatingResponse;
import pokerface.pokerface.domain.detail.service.DetailService;

import static pokerface.pokerface.domain.detail.entity.Result.LOSE;
import static pokerface.pokerface.domain.detail.entity.Result.WIN;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping("/{detailId}")
    public DetailResponse getDetailInfo(@PathVariable Long detailId){
        return detailService.getDetail(detailId);
    }

    @GetMapping
    public ExpectRatingResponse expectRating(@PathVariable Long memberId, @PathVariable Long opponentId ){
        return ExpectRatingResponse.of(
                detailService.calculateRating(0, 0, 0, 0, WIN),
                detailService.calculateRating(0, 0, 0, 0, LOSE));
    }
}
