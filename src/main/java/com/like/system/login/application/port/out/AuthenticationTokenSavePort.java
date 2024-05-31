package com.like.system.login.application.port.out;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.domain.SystemUser;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationTokenSavePort {
	AuthenticationToken SaveAuthenticationToken(LoginRequestDTO dto, SystemUser systemUser, List<MenuGroupSaveDTO> menuGroupList, HttpServletRequest request);
	
	void process(LoginRequestDTO dto, Collection<? extends GrantedAuthority> getAuthorities, AuthenticationToken authToken, HttpServletRequest request);
}
