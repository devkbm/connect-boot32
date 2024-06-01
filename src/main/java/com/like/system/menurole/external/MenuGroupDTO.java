package com.like.system.menurole.external;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.system.menu.domain.MenuGroup;

import lombok.Builder;

@Builder
public record MenuGroupDTO(
		LocalDateTime createdDt,
		String createdBy,
		LocalDateTime modifiedDt,
		String modifiedBy,	
		String clientAppUrl,			
		String companyCode,
		String menuGroupCode,		
		String menuGroupName,
		String menuGroupUrl,
		String description
		) implements Serializable {	
	
	public static MenuGroupDTO toDTO(MenuGroup entity) {
		if (entity == null) return null;
		
		return MenuGroupDTO.builder()
							.createdDt(entity.getCreatedDt())
							.createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
							.modifiedDt(entity.getModifiedDt())
							.modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())								
							.companyCode(entity.getId().getCompanyCode())
							.menuGroupCode(entity.getId().getMenuGroupCode())
							.menuGroupName(entity.getName())
							.menuGroupUrl(entity.getMenuGroupUrl())
							.description(entity.getDescription())
							.build();
	}
}
