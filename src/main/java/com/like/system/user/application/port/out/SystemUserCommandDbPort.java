package com.like.system.user.application.port.out;

import java.util.List;

import com.like.system.user.domain.SystemUser;

public interface SystemUserCommandDbPort {
	SystemUser select(String companyCode, String userId);
	
	List<SystemUser> select(String companyCode, List<String> userIds);
	
	void save(SystemUser entity);
	
	void delete(String companyCode, String userId);
}
