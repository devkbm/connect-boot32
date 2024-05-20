package com.like.system.user.application.port.in;

import com.like.system.user.dto.SystemUserProfileDTO;
import com.like.system.user.dto.SystemUserProfileSessionDTO;

public interface SystemUserProfileSelectUseCase {

	SystemUserProfileDTO select(String companyCode, String userId, SystemUserProfileSessionDTO dto);
}
