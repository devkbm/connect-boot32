package com.like.system.menurole.application.service;

import org.springframework.util.StringUtils;

import com.like.system.menurole.dto.MenuRoleHierarchyNgZorro;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuRoleHierarchyHalfChecker {

	public static void setHalfChecked(MenuRoleHierarchyNgZorro dto) {
		
		log.info("key : " + dto.key + " menuChildrenCount : "+ menuChildrenCount(dto) + " menuRoleChildrenCount : " + menuRoleChildrenCount(dto));
		
		if (dto.children != null) {
			for ( MenuRoleHierarchyNgZorro child : dto.children ) {
				MenuRoleHierarchyHalfChecker.setHalfChecked(child);											
			}
		}		
						
		// 하위 메뉴는 존재하나 일부 하위 메뉴롤만 선택되어있을 경우 UI에서 Half Checked 표시를 위해 checked를 변경
		if (menuChildrenCount(dto) > 0 &&  menuChildrenCount(dto) > menuRoleChildrenCount(dto)) {
			dto.halfChecked = true;
			dto.checked = false;			
		}						
		
		// 하위 노드중에 Half Checked가 있다면 checked 상태 변경
		if (dto.children != null && dto.children.stream().filter(e -> e.halfChecked).count() > 0) {
			dto.checked = false;			
		}
	}
	
	private static int menuChildrenCount(MenuRoleHierarchyNgZorro dto) {
		return dto.children == null ? 0 : dto.children.size();
	}
	
	private static long menuRoleChildrenCount(MenuRoleHierarchyNgZorro dto) {
		return dto.children == null ? 0 :  dto.children.stream().filter(e -> StringUtils.hasText(e.getRoleCode())).count();
	}
}
