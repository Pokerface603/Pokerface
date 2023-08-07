package pokerface.pokerface.config.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pokerface.pokerface.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.Collection;

// UserDetails : 스프링 세큐리티 세션에 Users 객체를 UserDetails 타입으로 저장한다.
public class PrincipalDetails implements UserDetails{ // 스프링 세큐리티에서 사용할 유저 정보
	
	private Member member;

	public PrincipalDetails(Member member) {
		this.member = member;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// A : 관리자, C : 일반 회원
		authorities.add(new GrantedAuthority() { // 현재 서비스는 유저 당 역할을 하나만 지니는 상태이지만 유저 당 역할이 여러개 인 경우도 있으므로 Collection 사용
          @Override
          public String getAuthority() {
			  return "ROLE_" + member.getUserRole(); // role 을 받을 때 앞에 "ROLE_" 붙이는 것(prefix)이 스프링의 규칙, 즉 ROLE_USER 같은 식으로 리턴됨
		  }
     	 });
		
		return null;
	}
	
	public Member getMember() {
		return member;
	}
	
	public Long getMemberId() {
		return member.getId();
	}

	@Override
	public String getPassword() {
		return member.getUserPassword();
	}


	@Override
	public String getUsername() {
		return member.getEmail();
	}
	
	/**
	 * 이 밑에서 부터는 다 true를 return하도록 수정
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
