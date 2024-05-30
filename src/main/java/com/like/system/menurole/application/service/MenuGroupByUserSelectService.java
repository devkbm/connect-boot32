package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.dto.MenuGroupSaveDTO;
import com.like.system.menurole.application.port.in.external.MenuGroupByUserSelectUseCase;
import com.like.system.menurole.application.port.out.MenuGroupByRolesSelectDbPort;
import com.like.system.user.application.port.in.SystemUserSelectUseCase;
import com.like.system.user.dto.SystemUserSaveDTO;

@Service
public class MenuGroupByUserSelectService implements MenuGroupByUserSelectUseCase {

	MenuGroupByRolesSelectDbPort dbPort;
	SystemUserSelectUseCase userSelectUseCase;
	
	MenuGroupByUserSelectService(MenuGroupByRolesSelectDbPort dbPort,
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
