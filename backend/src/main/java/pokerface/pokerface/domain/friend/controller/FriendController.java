package pokerface.pokerface.domain.friend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.friend.dto.request.FriendRequest;
import pokerface.pokerface.domain.friend.dto.response.FriendResponse;
import pokerface.pokerface.domain.friend.service.FriendService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/friends")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    @GetMapping
    public ResponseEntity<List<FriendResponse>> friendListAll(){
        return new ResponseEntity<>(friendService.findAll().stream()
                .map(FriendResponse::of)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping("/{friendId}")
    public ResponseEntity<FriendResponse> getFriend(@PathVariable Long friendId){
        return new ResponseEntity<>(friendService.getFriend(friendId), HttpStatus.OK);
    }

    @GetMapping("/from/{memberId}")
    public ResponseEntity<List<FriendResponse>> fromFriendListByMemberId(@PathVariable Long memberId){
        return new ResponseEntity<>(friendService.findByFromId(memberId).stream()
                .map(FriendResponse::of)
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> friendRegistry(@RequestBody @Validated FriendRequest friendRequest){
        friendService.save(friendRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
