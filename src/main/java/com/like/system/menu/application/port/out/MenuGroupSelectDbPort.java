package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.dto.MenuGroupQueryDTO;

public interface MenuGroupSelectDbPort {
	MenuGroup select(String companyCode, String menuGroupCode);
	
	List<MenuGroup> selectList(MenuGroupQueryDTO dto);
}
