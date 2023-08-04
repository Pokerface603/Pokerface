package pokerface.pokerface.domain.friend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.domain.friend.dto.request.FriendRequest;
import pokerface.pokerface.domain.friend.dto.response.FriendResponse;
import pokerface.pokerface.domain.friend.entity.Friend;
import pokerface.pokerface.domain.friend.repository.FriendRepository;
import pokerface.pokerface.domain.member.service.MemberService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final MemberService memberService;

    public List<Friend> findAll(){
        return friendRepository.findAll();
    }

    public Friend findById(Long friendId) {
        return friendRepository.findById(friendId).orElseThrow(IllegalAccessError::new);
    }

    public FriendResponse getFriend(Long friendId){
        return FriendResponse.of(findById(friendId));
    }

    public List<Friend> findByFromId(Long fromId){
        return friendRepository.findFriendByFromId(fromId);
    }

    public void save(FriendRequest friendRequest){
        friendRepository.save(friendRequest.toFriend(
                memberService.findById(friendRequest.getFromId()),
                memberService.findById(friendRequest.getToId())
        ));
    }
}
