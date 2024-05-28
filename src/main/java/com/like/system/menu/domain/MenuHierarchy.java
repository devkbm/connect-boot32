package com.like.system.menu.domain;

import java.util.List;

import lombok.Getter;

@Getter()
public class MenuHierarchy {

	String companyCode;
	
	String menuGroupCode;
	
	String menuCode;
	
	String menuName;
	
	MenuType menuType;
	
	String appUrl;
	
	long sequence;
	
	long level;
	
	String parentMenuCode;
	
	List<MenuHierarchy> children;
	
	boolean isLeaf;
	
	public MenuHierarchy() {}
	
	public String menuCode() {
		return this.menuCode;
	}
	
	public String parentMenuCode() {
		return this.parentMenuCode;
	}
	
	public List<MenuHierarchy> children() {
		return this.children;
	}
	
	public void setChildren(List<MenuHierarchy> children) {
		this.children = children;
	}
	
	public void isLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
}
