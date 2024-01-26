package com.like.system.user.application.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.like.system.dept.application.port.out.DeptCommandDbPort;
import com.like.system.dept.domain.Dept;
import com.like.system.user.application.port.dto.SystemUserSaveDTO;
import com.like.system.user.application.port.in.SystemUserSaveUseCase;
import com.like.system.user.application.port.out.SystemUserCommandDbPort;
import com.like.system.user.application.port.out.SystemUserRoleCommandDbPort;
import com.like.system.user.domain.SystemUser;
import com.like.system.user.domain.SystemUserRole;
import com.like.system.user.domain.vo.UserPassword;

@Transactional
@Service
public class SystemUserSaveService implements SystemUserSaveUseCase {

	SystemUserCommandDbPort dbPort;	
	DeptCommandDbPort deptDbPort;
	SystemUserRoleCommandDbPort userRoleDbPort;
	PasswordEncoder passwordEncoder;
	
	SystemUserSaveService(SystemUserCommandDbPort dbPort,
						  DeptCommandDbPort deptDbPort,
						  SystemUserRoleCommandDbPort userRoleDbPort,
						  PasswordEncoder passwordEncoder
						  ) {
		this.dbPort = dbPort;
		this.deptDbPort = deptDbPort;
		this.userRoleDbPort = userRoleDbPort;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public void save(SystemUserSaveDTO dto) {
		Dept dept = StringUtils.hasText(dto.deptCode()) ? deptDbPort.select(dto.companyCode(), dto.deptCode()).orElse(null) : null;
		SystemUser user = this.dbPort.select(dto.companyCode(), dto.userId());
		
		if (user == null) {
			user = dto.newUser(dept);
			user.changePassword(passwordEncoder.encode(UserPassword.getInitPassword()));
		} else {
			dto.modifyUser(user, dept);
		}							
				
		this.dbPort.save(user);
		
		this.userRoleDbPort.delete(user.getRoleList().stream().toList());
						
		this.userRoleDbPort.save(this.toSystemUserRole(dto, user));
	}
	
	private List<SystemUserRole> toSystemUserRole(SystemUserSaveDTO dto, SystemUser user) {
		return this.userRoleDbPort.select(dto.companyCode(), dto.roleList())
								  .stream()
								  .map(e -> new SystemUserRole(user, e))
								  .toList();
	}

}
