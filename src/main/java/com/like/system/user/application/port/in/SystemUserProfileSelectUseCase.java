package com.like.system.user.application.port.in;

import com.like.system.user.application.port.dto.SystemUserProfileDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface SystemUserProfileSelectUseCase {

	SystemUserProfileDTO select(String companyCode, String userId, HttpServletRequest request);
}
