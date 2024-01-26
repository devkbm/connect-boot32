package com.like.system.user.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRole;
import com.like.system.role.adapter.out.persistence.jpa.entity.JpaRoleId;
import com.like.system.role.adapter.out.persistence.jpa.repository.RoleJpaRepository;
import com.like.system.user.adapter.out.persistence.jpa.repository.SystemUserRoleRepository;
import com.like.system.user.application.port.out.SystemUserRoleCommandDbPort;
import com.like.system.user.domain.SystemUserRole;

@Repository
public class SystemUserRoleCommandAdapter implements SystemUserRoleCommandDbPort {

	SystemUserRoleRepository repository;
	RoleJpaRepository roleRepository;
	
	SystemUserRoleCommandAdapter(SystemUserRoleRepository repository,
							  RoleJpaRepository roleRepository) {
		this.repository = repository;
		this.roleRepository = roleRepository;
	}
	
	@Override
	public List<JpaRole> select(String companyCode, List<String> roles) {
		return roleRepository.findAllById(roles.stream()
				   			 				   .map(r -> new JpaRoleId(companyCode, r))
				   			 				   .toList());
	}

	@Override
	public void save(List<SystemUserRole> roleList) {
		repository.saveAll(roleList);
	}

	@Override
	public void delete(List<SystemUserRole> roleList) {
		repository.deleteAll(roleList);		
	}
	
}
