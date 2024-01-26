package com.like.system.user.application.port.out;

import com.like.system.user.domain.SystemUser;

public interface SystemUserCommandDbPort {
	SystemUser select(String companyCode, String userId);
	
	void save(SystemUser entity);
	
	void delete(String companyCode, String userId);
}
