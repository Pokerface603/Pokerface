package pokerface.pokerface.domain.detail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.service.DetailService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping
    public ResponseEntity<List<DetailResponse>> detailListAll(){
        return new ResponseEntity<>(detailService.findAll().stream()
                .map(DetailResponse::of)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<DetailResponse>> detailListByMemberId(@PathVariable Long memberId){
        return new ResponseEntity<>(detailService.findByMemberId(memberId).stream()
                .map(DetailResponse::of)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{detailId}")
    public ResponseEntity<DetailResponse> getDetailInfo(@PathVariable Long detailId){
        return new ResponseEntity<>(detailService.getDetail(detailId), HttpStatus.OK);
    }

    @GetMapping("/count/{memberId}")
    public ResponseEntity<Long> countByMemberId(@PathVariable Long memberId){
        return new ResponseEntity<>(detailService.countByMemberId(memberId), HttpStatus.OK);
    }

    @GetMapping("/count/{memberId}/win")
    public ResponseEntity<Long> countWinByMemberId(@PathVariable Long memberId){
        return new ResponseEntity<>(detailService.countWinByMemberId(memberId), HttpStatus.OK);
    }
}
