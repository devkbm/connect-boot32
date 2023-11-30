package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.application.port.dto.MenuRoleMappingSaveDTO;

public interface MenuRoleMappingSaveUseCase {

	void save(List<MenuRoleMappingSaveDTO> entityList);
}
