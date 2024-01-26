package com.like.system.user.application.port.dto;

public record PasswordChangeRequestDTO(
		String companyCode,
		String userId,
		String beforePassword,
		String afterPassword
		) {	
}