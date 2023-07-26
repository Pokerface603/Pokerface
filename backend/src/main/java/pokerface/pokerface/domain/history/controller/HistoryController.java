package pokerface.pokerface.domain.history.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.history.service.HistoryService;

@RestController
@RequestMapping("/histories")
@RequiredArgsConstructor
public class HistoryController {
    private final HistoryService historyService;
}
