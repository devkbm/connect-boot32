package com.like.system.menu.application.port.out;

import java.util.List;

import com.like.system.menu.domain.Menu;
import com.like.system.menu.dto.MenuQueryDTO;

public interface MenuSelectDbPort {
	Menu select(String companyCode, String menuGroupCode, String menuCode);
	
	List<Menu> selectList(MenuQueryDTO dto);
}
