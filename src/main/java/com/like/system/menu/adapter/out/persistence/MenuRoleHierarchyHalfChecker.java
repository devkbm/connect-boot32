package com.like.system.menu.adapter.out.persistence;

import com.like.system.menu.application.port.dto.MenuRoleMappingHierarchyResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuRoleHierarchyHalfChecker {

	public static void setHalfChecked(MenuRoleMappingHierarchyResponseDTO dto) {
		
		log.info("key : " + dto.key + " menuChildrenCount : "+ dto.menuChildrenCount + " menuRoleChildrenCount : " + dto.menuRoleChildrenCount);
		
		if (dto.children != null) {
			for ( MenuRoleMappingHierarchyResponseDTO child : dto.children ) {
				MenuRoleHierarchyHalfChecker.setHalfChecked(child);											
			}
		}		
						
		// 하위 메뉴는 존재하나 일부 하위 메뉴롤만 선택되어있을 경우 UI에서 Half Checked 표시를 위해 checked를 변경
		if (dto.menuChildrenCount > 0 &&  dto.menuChildrenCount > dto.menuRoleChildrenCount) {
			dto.halfChecked = true;
			dto.checked = false;			
		}						
		
		// 하위 노드중에 Half Checked가 있다면 checked 상태 변경
		if (dto.children != null && dto.children.stream().filter(e -> e.halfChecked).count() > 0) {
			dto.checked = false;			
		}
	}
}
