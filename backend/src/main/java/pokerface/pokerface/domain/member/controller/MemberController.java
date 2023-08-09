package pokerface.pokerface.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pokerface.pokerface.config.email.service.MailService;
import pokerface.pokerface.domain.member.dto.request.MemberJoinReq;
import pokerface.pokerface.domain.member.dto.response.RankingRes;
import pokerface.pokerface.domain.member.service.MemberService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final MailService mailService;

    /**
     * 회원 가입
     */
    @PostMapping()
    public ResponseEntity<Void> join(@RequestBody MemberJoinReq memberJoinReq) throws Exception {
        log.debug("====join Called====");

        String authKey = mailService.sendEmail(memberJoinReq.getEmail()); // 이메일 인증 authKey 저장
        memberJoinReq.setAuthKey(authKey);

        memberJoinReq.setPassword(passwordEncoder.encode(memberJoinReq.getPassword())); // 회원가입 진행
        memberService.join(memberJoinReq);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/email/confirm")
    public ResponseEntity<Void> emailConfirm(@RequestParam String email, @RequestParam String authKey) {
        memberService.updateEmailAuth(email, authKey);
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

    /*
     * 랭킹 관련 API
     * */
    @GetMapping("/ranking")
    public ResponseEntity<List<RankingRes>> findAllRankings(){
        return new ResponseEntity<>(memberService.findAllRankings(), HttpStatus.OK);
    }

    @GetMapping("/ranking/top")
    public ResponseEntity<List<RankingRes>> findTop5Rankings(){
        return new ResponseEntity<>(memberService.findTop5Rankings(), HttpStatus.OK);
    }

    @GetMapping("/ranking/{memberId}")
    public ResponseEntity<RankingRes> findRankingByMemberId(@PathVariable Long memberId){
        return new ResponseEntity<>(memberService.findRankingByMemberId(memberId), HttpStatus.OK);
    }
}
