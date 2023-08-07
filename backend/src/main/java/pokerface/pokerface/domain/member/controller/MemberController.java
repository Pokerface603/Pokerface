package pokerface.pokerface.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.domain.member.dto.request.MemberJoinReq;
import pokerface.pokerface.domain.member.service.MemberService;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;

    /**
     * 회원 가입
     */
    @PostMapping()
    public ResponseEntity<Void> join(@RequestBody MemberJoinReq memberJoinReq) throws Exception{

        log.debug("/user/join");

        String email = memberJoinReq.getEmail();
        if(memberService.emailCheck(email)) {
            log.debug("이미 존재하는 이메일 입니다.");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // 유저 패스워드 암호화
        String userPassword =  passwordEncoder.encode(memberJoinReq.getPassword());
        memberJoinReq.setPassword(userPassword);

        memberService.join(memberJoinReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check/email/{email}")
    public ResponseEntity<Boolean> emailDuplicatedCheck(@PathVariable String email) {
        return new ResponseEntity<>(memberService.emailCheck(email), HttpStatus.OK);
    }

    @GetMapping("/check/nickname/{nickname}")
    public ResponseEntity<Boolean> nicknameDuplicatedCheck(@PathVariable String nickname) {
        return new ResponseEntity<>(memberService.nicknameCheck(nickname), HttpStatus.OK);
    }
}
