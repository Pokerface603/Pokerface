package pokerface.pokerface.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.member.dto.request.MemberRequest;
import pokerface.pokerface.domain.member.dto.response.MemberResponse;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private MemberService memberService;

    @GetMapping
    public List<MemberResponse> memberListAll(){
        return memberService.findAll().stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{memberId}")
    public MemberResponse getMemberInfo(@PathVariable Long memberId){
        return MemberResponse.of(memberService.findById(memberId));
    }

    @PostMapping
    public void memberRegistry(@RequestBody @Validated MemberRequest memberRequest){
        memberService.save(memberRequest);
    }
}
