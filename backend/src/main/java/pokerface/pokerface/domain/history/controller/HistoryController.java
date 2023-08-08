package pokerface.pokerface.domain.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.history.dto.request.HistoryRequest;
import pokerface.pokerface.domain.history.dto.response.HistoryResponse;
import pokerface.pokerface.domain.history.service.HistoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;

    @GetMapping
    public ResponseEntity<List<HistoryResponse>> historyListAll(){
        return new ResponseEntity<>(historyService.findAll().stream()
                .map(history -> HistoryResponse.of(history, historyService.convertGameLogToData(history.getGameLog(), true)))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{historyId}/{memberId}")
    public ResponseEntity<HistoryResponse> getHistory(@PathVariable Long historyId, @PathVariable Long memberId){
        return new ResponseEntity<>(historyService.getHistory(historyId, memberId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> historyRegistry(@RequestBody @Validated HistoryRequest historyRequest){
        historyService.save(historyRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
