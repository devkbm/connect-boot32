package com.like.system.menu.domain;

import java.util.List;

public class MenuHierarchy {

	String companyCode;
	
	String menuGroupCode;
	
	String menuCode;
	
	String menuName;
	
	String menuType;
	
	String parentMenuCode;
	
	List<MenuHierarchy> children;
	
	boolean isLeaf;
	
	public MenuHierarchy() {}
}
