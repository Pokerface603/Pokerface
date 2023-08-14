package pokerface.pokerface.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pokerface.pokerface.config.error.RestException;
import pokerface.pokerface.config.error.errorcode.ErrorCode;
import pokerface.pokerface.domain.friend.dto.request.FriendRequest;
import pokerface.pokerface.domain.friend.dto.response.FriendResponse;
import pokerface.pokerface.domain.friend.entity.Friend;
import pokerface.pokerface.domain.friend.repository.FriendRepository;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberService memberService;

    public List<Friend> findAll(){
        return friendRepository.findAll();
    }

    public Friend findById(Long friendId) {
        return friendRepository.findById(friendId).orElseThrow(() -> new RestException(ErrorCode.RESOURCE_NOT_FOUND));
    }

    public FriendResponse getFriend(Long friendId) {
        return FriendResponse.of(findById(friendId));
    }

    public List<String> findFriendsByFromEmail(String email) {
        return friendRepository.findByFromEmail(email).stream()
                .map(friend -> friend.getTo().getEmail())
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(FriendRequest friendRequest){
        friendRepository.save(friendRequest.toFriend(
                memberService.findById(friendRequest.getFromId()),
                memberService.findById(friendRequest.getToId())
        ));
    }

    @Transactional
    public boolean save(String firstMemberEmail, String secondMemberEmail){
        if(findFriendsByFromEmail(firstMemberEmail).contains(secondMemberEmail)){
            return false;
        }

        friendRepository.save(new Friend(memberService.findByEmail(firstMemberEmail), memberService.findByEmail(secondMemberEmail)));
        friendRepository.save(new Friend(memberService.findByEmail(secondMemberEmail), memberService.findByEmail(firstMemberEmail)));
        return true;
    }
}
