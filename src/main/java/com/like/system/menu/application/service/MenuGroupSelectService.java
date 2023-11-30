package com.like.system.menu.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.like.system.menu.application.port.dto.MenuGroupQueryDTO;
import com.like.system.menu.application.port.dto.MenuGroupSaveDTO;
import com.like.system.menu.application.port.in.MenuGroupSelectUseCase;
import com.like.system.menu.application.port.out.MenuGroupSelectDbPort;

@Service
public class MenuGroupSelectService implements MenuGroupSelectUseCase {

	MenuGroupSelectDbPort port;
	
	MenuGroupSelectService(MenuGroupSelectDbPort port) {
		this.port = port;
	}
	
	@Override
	public MenuGroupSaveDTO select(String organizationCode, String menuGroupCode) {
		return MenuGroupSaveDTO.toDTO(this.port.select(organizationCode, menuGroupCode));
	}

	@Override
	public List<MenuGroupSaveDTO> selectList(MenuGroupQueryDTO dto) {
		return this.port.selectList(dto)
						.stream()
						.map(e -> MenuGroupSaveDTO.toDTO(e))
						.toList();
	}

}
