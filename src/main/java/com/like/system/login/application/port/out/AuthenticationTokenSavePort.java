package com.like.system.login.application.port.out;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.permission.domain.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationTokenSavePort {
	void process(LoginRequestDTO dto, Collection<? extends GrantedAuthority> getAuthorities, AuthenticationToken authToken, HttpServletRequest request);
}
