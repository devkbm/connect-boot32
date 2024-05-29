package com.like.system.menurole.domain;

import java.util.List;

import lombok.Getter;

@Getter
public class MenuRoleHierarchy {

	String companyCode;
	
	String menuGroupCode;
	
	String menuCode;
	
	String roleCode;
	
	String parentMenuCode;
	
	List<MenuRoleHierarchy> children;
	
	boolean checked;
	
	boolean isLeaf;
		
	public long menuRoleChildrenCount;
	
	public MenuRoleHierarchy() {}
	
	public String menuCode() {
		return this.menuCode;
	}
	
	public String parentMenuCode() {
		return this.parentMenuCode;
	}
	
	public void setChildren(List<MenuRoleHierarchy> children) {
		this.children = children;
	}
	
	public void isLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
