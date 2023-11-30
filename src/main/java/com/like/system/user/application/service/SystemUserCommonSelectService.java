package com.like.system.user.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRepository;
import com.like.system.user.application.port.in.share.SystemUserCommonSelectUseCase;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserId;

@Service
public class SystemUserCommonSelectService implements SystemUserCommonSelectUseCase {

	SystemUserRepository repository;
	
	SystemUserCommonSelectService(SystemUserRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public SystemUser findUser(String orginizationCode, String userId) {
		return this.repository.findById(new SystemUserId(orginizationCode, userId)).orElse(null);
	}
	
	@Override
	public List<SystemUser> findUsers(List<SystemUserId> userIds) {
		return this.repository.findAllById(userIds);
	}

}
