package com.like.system.menu.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.like.system.menu.domain.MenuGroup;

import lombok.Builder;

@Builder
public record MenuGroupSaveDTO(
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
	
	public MenuGroup newMenuGroup() {
		MenuGroup entity = MenuGroup.builder()
								    .companyCode(this.companyCode)
								    .code(this.menuGroupCode)
								    .name(this.menuGroupName)
								    .menuGroupUrl(this.menuGroupUrl)
								    .description(this.description)						    
								    .build();
		
		entity.setAppUrl(clientAppUrl);
		
		return entity;	
	}
	
	public void modifyMenuGroup(MenuGroup menuGroup) {
		menuGroup.modifyEntity(this.menuGroupName, this.menuGroupUrl, this.description);
		
		menuGroup.setAppUrl(clientAppUrl);
	}
	
	public static MenuGroupSaveDTO toDTO(MenuGroup entity) {
		if (entity == null) return null;
		
		return MenuGroupSaveDTO.builder()
							.createdDt(entity.getCreatedDt())
							.createdBy(entity.getCreatedBy() == null ? null : entity.getCreatedBy().getLoggedUser())
							.modifiedDt(entity.getModifiedDt())
							.modifiedBy(entity.getModifiedBy() == null ? null : entity.getModifiedBy().getLoggedUser())								
							.companyCode(entity.getId().getCompanyCode())
							//.menuGroupId(entity.getId())
							.menuGroupCode(entity.getId().getMenuGroupCode())
							.menuGroupName(entity.getName())
							.menuGroupUrl(entity.getMenuGroupUrl())
							.description(entity.getDescription())
							.build();
	}
}
