package com.like.system.user.external;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.user.domain.SystemUser;

import lombok.Builder;

@Builder
public record SystemUserLoginDTO(
		String companyCode,	
		String staffNo,
		List<MenuGroupSaveDTO> menuGroupList,
		Collection<? extends GrantedAuthority> authorities
		) {

	public static SystemUserLoginDTO toDTO(SystemUser entity) {
		if (entity == null) return null;
		
		return SystemUserLoginDTO
				.builder()
				.companyCode(entity.getId().getCompanyCode())
				.staffNo(entity.getId().getUserId())
				.authorities(entity.getAuthorities())
				.build();
	}
}
