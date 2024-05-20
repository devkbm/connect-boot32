package com.like.system.menurole.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.menurole.application.port.in.MenuRoleMappingSaveUseCase;
import com.like.system.menurole.application.port.out.MenuRoleMappingSaveDbPort;
import com.like.system.menurole.dto.MenuRoleMappingSaveDTO;

@Transactional
@Service
public class MenuRoleMappingSaveService implements MenuRoleMappingSaveUseCase {

	MenuRoleMappingSaveDbPort dbPort;
	
	MenuRoleMappingSaveService(MenuRoleMappingSaveDbPort dbPort) {
		this.dbPort = dbPort;
	}
	
	@Override
	public void save(List<MenuRoleMappingSaveDTO> dtoList) {
		this.dbPort.clear(dtoList.get(0).companyCode(), dtoList.get(0).menuGroupCode(), dtoList.get(0).roleCode());
		
		this.dbPort.save(dtoList.stream().map(e -> e.toEntity()).toList());		
	}

}
