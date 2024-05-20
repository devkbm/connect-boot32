package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.in.MenuGroupSelectUseCase;
import com.like.system.menu.application.port.out.MenuGroupSelectDbPort;
import com.like.system.menu.dto.MenuGroupQueryDTO;
import com.like.system.menu.dto.MenuGroupSaveDTO;

@Service
public class MenuGroupSelectService implements MenuGroupSelectUseCase {

	MenuGroupSelectDbPort port;
	
	MenuGroupSelectService(MenuGroupSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public MenuGroupSaveDTO select(String companyCode, String menuGroupCode) {
		return MenuGroupSaveDTO.toDTO(this.port.select(companyCode, menuGroupCode));
	}

	@Override
	public List<MenuGroupSaveDTO> selectList(MenuGroupQueryDTO dto) {
		return this.port.selectList(dto)
						.stream()
						.map(e -> MenuGroupSaveDTO.toDTO(e))
						.toList();
	}

}
