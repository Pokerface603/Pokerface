package pokerface.pokerface.config.login.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pokerface.pokerface.config.login.PrincipalDetails;
import pokerface.pokerface.domain.member.service.MemberService;

@Service
@Slf4j
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final MemberService memberService;
	
	/**
	 * 스프링이 로그인 요청이 들어오면 이를 가로채 username과 password 변수 2개를 가로챈다.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		log.info("PrincipalDetailsService.loadUserByUsername");
		return new PrincipalDetails(memberService.findByEmailPrincipalDetail(email).
				orElseThrow(() -> new UsernameNotFoundException("해당 이메일이 존재하지 않습니다.")));
	}

}
