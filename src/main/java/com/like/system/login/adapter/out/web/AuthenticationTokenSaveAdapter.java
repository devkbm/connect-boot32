package com.like.system.login.adapter.out.web;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import com.like.system.core.web.util.WebRequestUtil;
import com.like.system.login.application.port.dto.LoginRequestDTO;
import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

@Component
public class AuthenticationTokenSaveAdapter implements AuthenticationTokenSavePort {

	AuthenticationManager authenticationManager;
	
	AuthenticationTokenSaveAdapter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public AuthenticationToken SaveAuthenticationToken(LoginRequestDTO dto, SystemUser systemUser, List<MenuGroupSaveDTO> menuGroupList, HttpServletRequest request) {		
								
		AuthenticationToken authToken = createAuthToken(systemUser, menuGroupList, request);
		
		UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(dto.staffNo(), dto.password(), systemUser.getAuthorities());				
		securityToken.setDetails(authToken);
		
		Authentication authentication = authenticationManager.authenticate(securityToken); 					
		SecurityContextHolder.getContext().setAuthentication(authentication);
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
					
		return authToken;
	}

	private AuthenticationToken createAuthToken(SystemUser systemUser, List<MenuGroupSaveDTO> menuGroupList, HttpServletRequest request) {
		return AuthenticationToken.of(systemUser, menuGroupList, WebRequestUtil.getIpAddress(request), request.getSession().getId());
	}
	
}
