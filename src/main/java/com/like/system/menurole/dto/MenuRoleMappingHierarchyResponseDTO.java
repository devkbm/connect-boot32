package com.like.system.menurole.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuRoleMappingHierarchyResponseDTO {

	/* NzTreeNodeOptions */
	public String key;
	
	public String title;			
					
	public boolean checked;
	
	public boolean expanded;
	
	public boolean selected;
	
	@JsonProperty(value="isLeaf")
	public boolean isLeaf;
	
	public List<MenuRoleMappingHierarchyResponseDTO> children;
	/* NzTreeNodeOptions */
	
	public String menuGroupCode;
	
	public String menuCode;
	
	public String roleCode;
	
	public long menuChildrenCount;
	
	public long menuRoleChildrenCount;
	
	public boolean halfChecked;
	
	@QueryProjection
	public MenuRoleMappingHierarchyResponseDTO(
			String key, 
			String title,
			boolean checked,
			String menuGroupCode,
			String menuCode,
			String roleCode,
			long menuChildrenCount,
			long menuRoleChildrenCount) {
		this.key = key;
		this.title = title;
		this.checked = checked;
		this.expanded = false;
		this.selected = false;
		this.halfChecked = false;
		
		this.menuGroupCode = menuGroupCode;
		this.menuCode = menuCode;
		this.roleCode = roleCode;
			
		this.menuChildrenCount = menuChildrenCount;
		this.menuRoleChildrenCount = menuRoleChildrenCount;
						
	}
	
	public static MenuRoleMappingHierarchyResponseDTO build(MenuRoleHierarchy dto) {
		MenuRoleMappingHierarchyResponseDTO rec = new MenuRoleMappingHierarchyResponseDTO();
		
		rec.menuGroupCode = dto.getMenuGroupCode();
		rec.menuCode = dto.getMenuCode();
		rec.roleCode = dto.getRoleCode();
				
		
		return rec;
	}
	
	public void setChildren(List<MenuRoleMappingHierarchyResponseDTO> children) {
		this.children = children;
	}
	
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}	
	
}
