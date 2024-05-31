package com.like.system.login.application.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.like.core.web.util.WebRequestUtil;
import com.like.system.login.application.port.in.LoginUseCase;
import com.like.system.login.application.port.out.AuthenticationTokenSavePort;
import com.like.system.login.domain.event.LoginSuccessEvent;
import com.like.system.login.dto.LoginRequestDTO;
import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.menurole.application.port.in.external.MenuGroupByUserSelectUseCase;
import com.like.system.permission.application.port.in.AuthenticationTokenSelectUseCase;
import com.like.system.permission.domain.AuthenticationToken;
import com.like.system.user.application.port.in.external.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.external.SystemUserDTO;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class LoginService implements LoginUseCase {

	SystemUserCommonSelectUseCase userPort;
	MenuGroupByUserSelectUseCase menuGroupSelectUseCase;
	AuthenticationTokenSavePort	tokenPort;
	AuthenticationTokenSelectUseCase authTokenSelectUseCase;
	
	
	ApplicationEventPublisher publisher;
	
	public LoginService(SystemUserCommonSelectUseCase userPort
						,MenuGroupByUserSelectUseCase menuGroupSelectUseCase
						,AuthenticationTokenSavePort tokenPort
						,AuthenticationTokenSelectUseCase authTokenSelectUseCase
						,ApplicationEventPublisher publisher) {
		this.userPort = userPort;
		this.menuGroupSelectUseCase = menuGroupSelectUseCase;		
		this.tokenPort = tokenPort;
		this.authTokenSelectUseCase = authTokenSelectUseCase;
		this.publisher = publisher;
	}
	
	@Override
	public AuthenticationToken login(LoginRequestDTO dto, HttpServletRequest request) {
		String companyCode = dto.companyCode();
		String staffNo = dto.staffNo();
		String password = dto.password();
		
		// 로그인 요청정보 SpringSecurityUserService에서 사용하기 위해 THREAD_LOCAL에 저장
		LoginRequestContext.set(new LoginRequestDTO(companyCode, staffNo, password));
		
		SystemUser systemUser = userPort.findUser(companyCode, staffNo);
		List<MenuGroupSaveDTO> menuGroupList = menuGroupSelectUseCase.select(companyCode, staffNo);
					
		//AuthenticationToken token = tokenPort.SaveAuthenticationToken(dto, systemUser, menuGroupList, request);
		
		AuthenticationToken token2 = authTokenSelectUseCase.select(companyCode, staffNo, request.getSession().getId(), WebRequestUtil.getIpAddress(request));
		tokenPort.process(dto, systemUser.getAuthorities(), token2, request);
					
		// 로그인 요청정보 THREAD_LOCAL에서 제거
		LoginRequestContext.remove();

		this.publisher.publishEvent(new LoginSuccessEvent(companyCode, staffNo, LocalDate.now(), "login"));
		
		//return token;
		return token2;
	}
	
	

}
