package com.like.system.menu.application.port.out;

public interface MenuDeleteDbPort {
	void delete(String companyCode, String menuGroupCode, String menuCode);
}
