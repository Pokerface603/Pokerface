package pokerface.pokerface.domain.detail.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.detail.service.DetailService;

@RestController
@RequestMapping("/api/v1/details")
@RequiredArgsConstructor
public class DetailController {
    private final DetailService detailService;
}
