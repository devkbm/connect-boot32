package com.like.system.user.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.menu.dto.MenuHierarchyNgZorro;
import com.like.system.user.application.port.in.SystemUserMenuHierarchySelectUseCase;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SystemUserMenuHierarchyQuryController {

	SystemUserMenuHierarchySelectUseCase useCase;
	
	SystemUserMenuHierarchyQuryController(SystemUserMenuHierarchySelectUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/system/menuhierarchy/{userId}/{menuGroupCode}")
	public ResponseEntity<?> getMenuGroupHierachy(@RequestParam String companyCode, @PathVariable String userId, @PathVariable String menuGroupCode) {				
		
		log.info("companyCode : "+ companyCode);
		log.info("menuGroupCode : "+ menuGroupCode);
		List<MenuHierarchyNgZorro> menuGroup = useCase.select(companyCode, userId, menuGroupCode); 										
		
		return toList(menuGroup, MessageUtil.getQueryMessage(menuGroup.size()));
	}
}
