package com.gavoyage.config.login;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gavoyage.user.domain.Users;

// UserDetails : 스프링 세큐리티 세션에 Users 객체를 UserDetails 타입으로 저장한다.
public class PrincipalDetails implements UserDetails{ // 스프링 세큐리티에서 사용할 유저 정보
	
	private Users user;

	public PrincipalDetails(Users user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		// A : 관리자, C : 일반 회원
		authorities.add(new GrantedAuthority() { // 현재 서비스는 유저 당 역할을 하나만 지니는 상태이지만 유저 당 역할이 여러개 인 경우도 있으므로 Collection 사용

          @Override
          public String getAuthority() {
              return "ROLE_"+user.getUserRole(); // role 을 받을 때 앞에 "ROLE_" 붙이는 것(prefix)이 스프링의 규칙, 즉 ROLE_USER 같은 식으로 리턴됨
          }
      });
		
		return null;
	}
	
	public Users getUser() {
		return user;
	}
	
	public Long getUserIdx() {
		return user.getUserIdx();
	}

	@Override
	public String getPassword() {
		return user.getUserPassword();
	}


	@Override
	public String getUsername() {
		return user.getEmail();
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
