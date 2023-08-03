package pokerface.pokerface.domain.friend.controller;

import lombok.RequiredArgsConstructor;
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
    public List<FriendResponse> friendListAll(){
        return friendService.findAll().stream()
                .map(FriendResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{friendId}")
    public FriendResponse getFriend(@PathVariable Long friendId){
        return friendService.getFriend(friendId);
    }

    @GetMapping("/from/{memberId}")
    public List<FriendResponse>fromFriendListByMemberId(@PathVariable Long memberId){
        return friendService.findByFromId(memberId).stream()
                .map(FriendResponse::of)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void friendRegistry(@RequestBody @Validated FriendRequest friendRequest){
        friendService.save(friendRequest);
    }
}
