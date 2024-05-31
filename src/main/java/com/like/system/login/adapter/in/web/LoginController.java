package com.like.system.login.adapter.in.web;

import jakarta.servlet.http.HttpServletRequest;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.web.util.WebRequestUtil;
import com.like.system.login.application.port.in.LoginUseCase;
import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.login.application.service.LoginRequestContext;
import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.menurole.application.port.in.external.MenuGroupByUserSelectUseCase;
import com.like.system.permission.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.application.port.in.external.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;

@RestController
public class LoginController {		
		
	private LoginUseCase useCase;
		 
	SystemUserCommonSelectUseCase userPort;
	MenuGroupByUserSelectUseCase menuGroupSelectUseCase;
	AuthenticationTokenSavePort	tokenPort;
	AuthenticationTokenSelectUseCase authTokenSelectUseCase;
	
	ApplicationEventPublisher publisher;
	
	public LoginController(LoginUseCase useCase
						  ,SystemUserCommonSelectUseCase userPort
						  ,MenuGroupByUserSelectUseCase menuGroupSelectUseCase
						  ,AuthenticationTokenSavePort tokenPort
						  ,AuthenticationTokenSelectUseCase authTokenSelectUseCase
						  ,ApplicationEventPublisher publisher) {		
		this.useCase = useCase;
		
		this.userPort = userPort;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;		
		this.tokenPort = tokenPort;
		this.authTokenSelectUseCase = authTokenSelectUseCase;
		this.publisher = publisher;
	}
		 
	
	@PostMapping("/api/system/user/login")
	public AuthenticationToken login(@RequestBody @Valid LoginRequestDTO dto, HttpServletRequest request) {			
						         		 							                   
		//String ipAddress = WebRequestUtil.getIpAddress(request);
		//System.out.println("접속 IP주소: " + ipAddress);
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.set(dto);
		
		SystemUser systemUser = userPort.findUser(dto.companyCode(), dto.staffNo());
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(dto.companyCode(), dto.staffNo());
							
		AuthenticationToken token2 = authTokenSelectUseCase.select(dto.companyCode(), dto.staffNo(), request.getSession().getId(), WebRequestUtil.getIpAddress(request));
		tokenPort.process(dto, systemUser.getAuthorities(), token2, request);
		
		LoginRequestContext.remove();
		
		//return useCase.login(dto, request);
		return token2;
	}	
	
}
