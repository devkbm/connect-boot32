package com.like.system.user.export;

import java.util.List;

public interface SystemUserDTOSelectUseCase {
	SystemUserDTO findUser(String companyCode, String userId);	
	
	List<SystemUserDTO> findUsers(String companyCode, List<String> userId);
}
