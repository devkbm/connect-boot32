package com.like.system.menurole.application.port.dto;

import java.time.LocalDateTime;

import com.like.system.menurole.domain.MenuRoleMapping;
import com.like.system.menurole.domain.MenuRoleMappingId;

import lombok.Builder;

@Builder
public record MenuRoleMappingSaveDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,
		String clientAppUrl,	
		String companyCode,
		String menuGroupCode,
		String menuCode,
		String roleCode
		) {

	public MenuRoleMapping toEntity() {
		MenuRoleMapping entity = new MenuRoleMapping(new MenuRoleMappingId(companyCode, menuGroupCode, menuCode, roleCode));
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;
	}
	
	public static MenuRoleMappingSaveDTO toDTO(MenuRoleMapping entity) {
		return null;
	}
}
