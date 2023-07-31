package pokerface.pokerface.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pokerface.pokerface.domain.member.repository.MemberRepository;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private MemberRepository memberRepository;

}
