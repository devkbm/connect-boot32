package com.like.system.menurole.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.menurole.application.port.in.MenuRoleHierarchySelectUseCase;
import com.like.system.menurole.dto.MenuRoleMappingHierarchyResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MenuRoleHierarchyQueryController {

	MenuRoleHierarchySelectUseCase useCase;
	
	MenuRoleHierarchyQueryController(MenuRoleHierarchySelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/menurolehierarchy/{menuGroupCode}/{roleCode}")
	public ResponseEntity<?> getMenuGroupHierarchy(@RequestParam String companyCode, @PathVariable String menuGroupCode, @PathVariable String roleCode) {				
		
		log.info("companyCode : "+ companyCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<MenuRoleMappingHierarchyResponseDTO> menuGroup = useCase.select(companyCode, menuGroupCode, roleCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}	
}
