package com.like.system.role.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.role.application.port.in.RoleSelectUseCase;
import com.like.system.role.domain.Role;

@RestController
public class RoleSelectController {

	private RoleSelectUseCase useCase;
	
	public RoleSelectController(RoleSelectUseCase useCase) {
		this.useCase = useCase;
	}		
	
	@GetMapping("/api/system/role/{roleId}")
	public ResponseEntity<?> getAuthority(@RequestParam String companyCode, @PathVariable String roleId) {			
		
		Role authority = useCase.select(companyCode, roleId);										
		
		return toOne(authority, MessageUtil.getQueryMessage(authority == null ? 0 : 1));
	}
				
	
	
}
