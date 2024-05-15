package com.like.system.menurole.application.port.in;

import java.util.List;

import com.like.system.menurole.application.port.dto.MenuRoleMappingSaveDTO;

public interface MenuRoleMappingSaveUseCase {

	void save(List<MenuRoleMappingSaveDTO> entityList);
}
