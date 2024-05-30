package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.menurole.application.port.in.external.SystemUserMenuGroupSelectUseCase;
import com.like.system.menurole.application.port.out.SystemUserMenuGroupSelectDbPort;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.dto.SystemUserSaveDTO;

@Service
public class SystemUserMenuGroupSelectService implements SystemUserMenuGroupSelectUseCase {

	SystemUserMenuGroupSelectDbPort dbPort;
	SystemUserSelectUseCase userSelectUseCase;
	
	SystemUserMenuGroupSelectService(SystemUserMenuGroupSelectDbPort dbPort,
									 SystemUserSelectUseCase userSelectUseCase) {
		this.dbPort = dbPort;
		this.userSelectUseCase = userSelectUseCase;
	}
	
	@Override
	public List<MenuGroupSaveDTO> select(String companyCode, String userId) {
		SystemUserSaveDTO userDTO = userSelectUseCase.selectDTO(companyCode, userId);
		
		return this.dbPort.select(companyCode, userDTO.roleList())
						  .stream()
						  .map(e -> MenuGroupSaveDTO.toDTO(e))
						  .toList();
	}

}
