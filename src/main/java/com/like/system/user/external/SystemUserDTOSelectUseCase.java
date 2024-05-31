package com.like.system.user.external;

public interface SystemUserDTOSelectUseCase {
	SystemUserDTO findUser(String orginizationCode, String userId);	
}
