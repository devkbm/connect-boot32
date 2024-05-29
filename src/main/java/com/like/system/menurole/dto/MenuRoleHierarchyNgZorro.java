package com.like.system.menurole.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.system.menurole.domain.MenuRoleHierarchy;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRoleHierarchyNgZorro {

	/* NzTreeNodeOptions */
	public String key;
	
	public String title;			
					
	public boolean checked;
	
	public boolean expanded;
	
	public boolean selected;
	
	@JsonProperty(value="isLeaf")
	public boolean isLeaf;
	
	public List<MenuRoleHierarchyNgZorro> children;
	/* NzTreeNodeOptions */
	
	public String menuGroupCode;
	
	public String menuCode;
	
	public String roleCode;
	
	public long menuChildrenCount;
	
	public long menuRoleChildrenCount;
	
	public boolean halfChecked;		
	
	public static MenuRoleHierarchyNgZorro build(MenuRoleHierarchy dto) {
		MenuRoleHierarchyNgZorro rec = new MenuRoleHierarchyNgZorro();
		
		rec.key = dto.getMenuCode();
		rec.title = dto.getMenuName();
		
		rec.menuGroupCode = dto.getMenuGroupCode();
		rec.menuCode = dto.getMenuCode();
		rec.roleCode = dto.getRoleCode();				
		
		rec.checked = dto.isChecked();
		rec.isLeaf = dto.isLeaf();
		
		return rec;
	}
	
	public void setChildren(List<MenuRoleHierarchyNgZorro> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}	
	
}
