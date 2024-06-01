package com.like.system.login.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;

import java.time.LocalDate;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebRequestUtil;
import com.like.system.login.domain.event.LoginSuccessEvent;
import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.permission.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.external.SystemUserLoginDTO;
import com.like.system.user.external.SystemUserLoginDTOSelectUseCase;

@RestController
public class LoginController {		
				
	SystemUserLoginDTOSelectUseCase userPort;
	AuthenticationTokenSelectUseCase authTokenSelectUseCase;
	
	AuthenticationManager authenticationManager;
	
	ApplicationEventPublisher publisher;
	
	public LoginController(SystemUserLoginDTOSelectUseCase userPort						  						 
						  ,AuthenticationTokenSelectUseCase authTokenSelectUseCase
						  ,AuthenticationManager authenticationManager
						  ,ApplicationEventPublisher publisher) {		
		
		this.userPort = userPort;			
		this.authTokenSelectUseCase = authTokenSelectUseCase;
		
		this.authenticationManager = authenticationManager;
		
		this.publisher = publisher;
	}
		 
	
	@PostMapping("/api/system/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpServletRequest request) {			
						         		 							                   
		//String ipAddress = WebRequestUtil.getIpAddress(request);
		//System.out.println("접속 IP주소: " + ipAddress);
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.set(dto);
										
		AuthenticationToken authToken = authTokenSelectUseCase.select(dto.companyCode(), dto.staffNo(), request.getSession().getId(), WebRequestUtil.getIpAddress(request));
				
		SystemUserLoginDTO systemUser = userPort.get(dto.companyCode(), dto.staffNo());
		UsernamePasswordAuthenticationToken securityToken = new UsernamePasswordAuthenticationToken(dto.staffNo(), dto.password(), systemUser.authorities());				
		securityToken.setDetails(authToken);
		
		Authentication authentication = authenticationManager.authenticate(securityToken); 					
		SecurityContextHolder.getContext().setAuthentication(authentication);
		request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		LoginRequestContext.remove();
		
		this.publisher.publishEvent(new LoginSuccessEvent(dto.companyCode(), dto.staffNo(), LocalDate.now(), "login"));
				
		return authToken;
	}	
	
}
