package com.like.system.authentication.application.port.in;

import com.like.system.authentication.domain.AuthenticationToken;

public interface AuthenticationTokenSelectUseCase {
	AuthenticationToken select(String companyCode, String userId, String sessionId, String ipAddress);
}
