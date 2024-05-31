package com.like.system.login.adapter.out.web;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;


import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.permission.domain.AuthenticationToken;


import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationTokenSaveAdapter implements AuthenticationTokenSavePort {

	AuthenticationManager authenticationManager;
	
	AuthenticationTokenSaveAdapter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}		
	
	public void process(LoginRequestDTO dto, Collection<? extends GrantedAuthority> getAuthorities, AuthenticationToken authToken, HttpServletRequest request) {
		UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(dto.staffNo(), dto.password(), getAuthorities);				
		securityToken.setDetails(authToken);
		
		Authentication authentication = authenticationManager.authenticate(securityToken); 					
		SecurityContextHolder.getContext().setAuthentication(authentication);
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	}
	
	
}
