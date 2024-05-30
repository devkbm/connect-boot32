package com.like.system.menurole.application.service;

import org.springframework.util.StringUtils;

import com.like.system.menurole.dto.MenuRoleHierarchyNgZorro;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MenuRoleHierarchyNgZorroHalfChecker {

	public static void setHalfChecked(MenuRoleHierarchyNgZorro dto) {
		
		log.info("key : " + dto.key + " menuChildrenCount : "+ menuChildrenCount(dto) + " menuRoleChildrenCount : " + menuRoleChildrenCount(dto));
		
		if (dto.children != null) {
			for ( MenuRoleHierarchyNgZorro child : dto.children ) {
				MenuRoleHierarchyNgZorroHalfChecker.setHalfChecked(child);											
			}
		}		
						
		// 하위 메뉴는 존재하나 일부 하위 메뉴롤만 선택되어있을 경우 UI에서 Half Checked 표시를 위해 checked를 변경
		// Ng-zorro Tree 컽트롤에서 하위 노드가 half Check 상태일때는 checked가 false여야 제대로 표시됨
		if (menuChildrenCount(dto) > 0 &&  menuChildrenCount(dto) > menuRoleChildrenCount(dto)) {
			dto.halfChecked = true;
			dto.checked = false;			
		}						
		
		// 하위 노드중에 Half Checked가 있다면 checked를 false로 변경
		if (isChildrenHalfChecked(dto)) {
			dto.checked = false;			
		}
	}
	
	private static int menuChildrenCount(MenuRoleHierarchyNgZorro dto) {
		return dto.children == null ? 0 : dto.children.size();
	}
	
	private static long menuRoleChildrenCount(MenuRoleHierarchyNgZorro dto) {
		return dto.children == null ? 0 :  dto.children.stream().filter(e -> StringUtils.hasText(e.getRoleCode())).count();
	}
	
	private static boolean isChildrenHalfChecked(MenuRoleHierarchyNgZorro dto) {
		return dto.children != null && dto.children.stream().filter(e -> e.halfChecked).count() > 0 ? true : false;
	}
}
