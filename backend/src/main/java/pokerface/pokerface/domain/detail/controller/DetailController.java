package pokerface.pokerface.domain.detail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.detail.dto.response.DetailCountResponse;
import pokerface.pokerface.domain.detail.dto.response.DetailResponse;
import pokerface.pokerface.domain.detail.service.DetailService;

import java.util.List;

@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;

    @GetMapping
    public ResponseEntity<List<DetailResponse>> detailListAll(){
        return new ResponseEntity<>(detailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/member/{email}")
    public ResponseEntity<List<DetailResponse>> detailListPagingByEmail(@PageableDefault(page = 0, size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable, @PathVariable String email){
        return new ResponseEntity<>(detailService.findPagingByMemberEmail(pageable, email), HttpStatus.OK);
    }

    @GetMapping("/{detailId}")
    public ResponseEntity<DetailResponse> getDetailInfo(@PathVariable Long detailId){
        return new ResponseEntity<>(detailService.getDetail(detailId), HttpStatus.OK);
    }

    @GetMapping("/count/{email}")
    public ResponseEntity<DetailCountResponse> countByMemberId(@PathVariable String email){
        return new ResponseEntity<>(detailService.countByMemberEmail(email), HttpStatus.OK);
    }
}
