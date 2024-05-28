package com.like.system.menu.dto;

import java.util.List;

import com.like.system.menu.domain.MenuHierarchy;
import com.like.system.menu.domain.MenuType;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuHierarchyNgZorro {
	
	/* NzTreeNodeOptions */
	String key;
	
	String title;			
					
	boolean expanded;
	
	boolean selected;
	
	boolean isLeaf;
	
	List<MenuHierarchyNgZorro> children;
	/* NzTreeNodeOptions */
	
	String menuGroupCode;
	
	String parentMenuCode;
	
	String menuType;
	
	Long sequence;
	
	Long level;
	
	String url;

	@QueryProjection
	public MenuHierarchyNgZorro(String key, String title, String menuGroupCode, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url) {				
		this.key = key;
		this.title = title;
		this.expanded = false;
		this.selected = false;
		this.menuGroupCode = menuGroupCode;
		this.parentMenuCode = parentMenuCode;
		this.menuType = menuType.toString();
		this.sequence = sequence;
		this.level = level;
		this.url = url;				
	}
	
	public static MenuHierarchyNgZorro build(MenuHierarchy dto) {
		MenuHierarchyNgZorro rec = new MenuHierarchyNgZorro();
		
		rec.menuGroupCode = dto.getMenuGroupCode();
		rec.parentMenuCode = dto.getParentMenuCode();
		rec.menuType = dto.getMenuType().toString();
		rec.sequence = dto.getSequence();
		rec.level = dto.getLevel();
		rec.url = dto.getAppUrl();
		
		rec.key = dto.getMenuCode();
		rec.title = dto.getMenuName();
		rec.expanded = false;
		rec.selected = false;
		
		return rec;
	}

	public void setChildren(List<MenuHierarchyNgZorro> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
