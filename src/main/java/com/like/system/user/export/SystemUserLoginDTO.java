package com.like.system.user.export;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.like.system.user.domain.SystemUser;

import lombok.Builder;

@Builder
public record SystemUserLoginDTO(
		String companyCode,	
		String staffNo,		
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
