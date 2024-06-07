package com.like.system.authentication.adapter.in.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.util.SessionUtil;
import com.like.core.web.util.WebRequestUtil;
import com.like.system.authentication.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.authentication.domain.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class AuthenticationTokenController {

	AuthenticationTokenSelectUseCase useCase;
	
	AuthenticationTokenController(AuthenticationTokenSelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/user/auth")
	public AuthenticationToken get(HttpServletRequest request, @RequestParam String companyCode) {
		
		return useCase.select(companyCode, 
							  SessionUtil.getUserId(), 
							  request.getSession().getId(), 
							  WebRequestUtil.getIpAddress(request));
	}
}
