package com.gavoyage.config.login.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.gavoyage.config.login.PrincipalDetails;
import com.gavoyage.user.service.UserServiceImpl;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService{
	
	private final UserServiceImpl userService;
	
	/**
	 * 스프링이 로그인 요청이 들어오면 이를 가로채 username과 password 변수 2개를 가로챈다.
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		System.out.println("PrincipalDetailsService loadUserByUsername calls");
		
		return new PrincipalDetails(userService.findByUserEmail(email)
									.orElseThrow(() -> new UsernameNotFoundException("해당 이메일과 일치하는 사용자가 존재하지 않습니다.")));
	}

}
