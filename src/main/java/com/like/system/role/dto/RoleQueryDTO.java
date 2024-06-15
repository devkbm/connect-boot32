package com.like.system.role.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleQueryDTO(
		@NotBlank(message="조직 코드를 선택해주세요.")
		String companyCode,
		String roleId,
		String roleCode,		
		String description,
		String menuGroupCode
		) {	
}
