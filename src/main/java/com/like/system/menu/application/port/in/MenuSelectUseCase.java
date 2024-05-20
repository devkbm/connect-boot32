package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuQueryDTO;
import com.like.system.menu.dto.MenuSaveDTO;

public interface MenuSelectUseCase {
	MenuSaveDTO select(String companyCode, String menuGroupCode, String menuCode);
	
	List<MenuSaveDTO> selectList(MenuQueryDTO dto);
}
