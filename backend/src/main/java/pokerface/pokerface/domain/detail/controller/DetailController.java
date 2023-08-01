package pokerface.pokerface.domain.detail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.detail.dto.request.DetailRequest;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.dto.response.ExpectRatingResponse;
import pokerface.pokerface.domain.detail.dto.response.SimpleDetailResponse;
import pokerface.pokerface.domain.detail.service.DetailService;

import java.util.List;
import java.util.stream.Collectors;

import static pokerface.pokerface.domain.detail.entity.Result.LOSE;
import static pokerface.pokerface.domain.detail.entity.Result.WIN;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping
    public List<DetailResponse> detailListAll(){
        return detailService.findAll().stream()
                .map(detail -> DetailResponse.of(detail, detailService.convertGameLogtoData(detail.getGameLog())))
                .collect(Collectors.toList());
    }

    @GetMapping("/member/{memberId}")
    public List<SimpleDetailResponse> detailListByMemberId(@PathVariable Long memberId){
        return detailService.findByMemberId(memberId).stream()
                .map(SimpleDetailResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{detailId}")
    public DetailResponse getDetailInfo(@PathVariable Long detailId){
        return detailService.getDetail(detailId);
    }

    // history 에서 호출하는 방식으로 변경
//    @PostMapping
//    public void detailRegistry(@RequestBody @Validated DetailRequest detailRequest, Long historyId, Long memberId){
//        detailService.save(detailRequest, historyId, memberId);
//    }

//    @GetMapping
//    public ExpectRatingResponse expectRating(@PathVariable Long memberId, @PathVariable Long opponentId ){
//        return ExpectRatingResponse.of(
//                detailService.calculateRating(0, 0, 0, 0, WIN),
//                detailService.calculateRating(0, 0, 0, 0, LOSE));
//    }
}
