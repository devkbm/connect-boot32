package com.like.system.user.dto;

public record PasswordChangeRequestDTO(
		String companyCode,
		String userId,
		String beforePassword,
		String afterPassword
		) {	
}