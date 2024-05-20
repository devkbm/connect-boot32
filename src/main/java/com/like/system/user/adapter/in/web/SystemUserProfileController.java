package com.like.system.user.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.core.util.SessionUtil;
import com.like.core.web.util.WebRequestUtil;
import com.like.system.user.application.port.in.SystemUserProfileSelectUseCase;
import com.like.system.user.dto.SystemUserProfileDTO;
import com.like.system.user.dto.SystemUserProfileSessionDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class SystemUserProfileController {		
				
	private SystemUserProfileSelectUseCase useCase;
		
	public SystemUserProfileController(SystemUserProfileSelectUseCase useCase) {
		this.useCase = useCase;
	}

	@GetMapping("/api/system/user/my-profile")
	public ResponseEntity<?> getUserProfile(HttpServletRequest request, @RequestParam String companyCode) throws FileNotFoundException, IOException {																		
				
		SystemUserProfileSessionDTO sessionDTO = new SystemUserProfileSessionDTO(WebRequestUtil.getIpAddress(request), new Date(request.getSession().getLastAccessedTime()));
		
		SystemUserProfileDTO dto = useCase.select(companyCode, SessionUtil.getUserId(), sessionDTO);					
						
		/*
		log.info("sessionId={}", session.getId());
		log.info("maxInactiveInterval={}", session.getMaxInactiveInterval());
		log.info("creationTime={}", new Date(session.getCreationTime()));
		log.info("lastAccessTjme={}",new Date(session.getLastAccessedTime()));
		log.info("isNew={}", session.isNew());
		
		String rtn = """
				sessionId=%s 
				maxInactiveInterval=%d
				creationTime=%s
				lastAccessTjme=%s
				isNew=%s
				""".formatted(session.getId()
							 ,session.getMaxInactiveInterval()
							 ,new Date(session.getCreationTime())
							 ,new Date(session.getLastAccessedTime())
							 ,session.isNew()); 
		
		 */
		
		return toOne(dto, MessageUtil.getQueryMessage(1));
	}			
}
