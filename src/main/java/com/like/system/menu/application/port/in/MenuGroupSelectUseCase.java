package com.like.system.menu.application.port.in;

import java.util.List;

import com.like.system.menu.dto.MenuGroupQueryDTO;
import com.like.system.menu.dto.MenuGroupSaveDTO;

public interface MenuGroupSelectUseCase {
	MenuGroupSaveDTO select(String companyCode, String menuGroupCode);
	
	List<MenuGroupSaveDTO> selectList(MenuGroupQueryDTO dto);
}
