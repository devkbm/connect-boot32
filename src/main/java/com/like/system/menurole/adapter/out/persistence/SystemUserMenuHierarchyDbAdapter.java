package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.QMenu;
import com.like.system.menu.dto.MenuHierarchyResponseDTO;
import com.like.system.menu.dto.QMenuHierarchyResponseDTO;
import com.like.system.menurole.application.port.out.SystemUserMenuHierarchySelectDbPort;
import com.like.system.menurole.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SystemUserMenuHierarchyDbAdapter implements SystemUserMenuHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	SystemUserMenuHierarchyDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuHierarchyResponseDTO> select(String companyCode, String menuGroupCode, List<String> roleCodes) {
		List<MenuHierarchyResponseDTO> rootList = this.getMenuRootList(companyCode, menuGroupCode, roleCodes);
		
		return this.getMenuHierarchyDTO(companyCode, roleCodes, rootList);
	}
	
	private List<MenuHierarchyResponseDTO> getMenuRootList(String companyCode, String menuGroupCode, List<String> roleCodes) {			
		
		JPAQuery<MenuHierarchyResponseDTO> query = queryFactory
				.select(projections(qMenu))
				.from(qMenu)
				.innerJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))	
					.and(qMenuRoleMapping.id.roleCode.in(roleCodes))
					)
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  ,qMenu.parentMenuCode.isNull()
					  );													
				
		return query.fetch();
	}
	
	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	private List<MenuHierarchyResponseDTO> getMenuHierarchyDTO(String companyCode, List<String> roleCodes, List<MenuHierarchyResponseDTO> list) {
		List<MenuHierarchyResponseDTO> children = null;
		
		for ( MenuHierarchyResponseDTO dto : list ) {			
			
			children = getMenuChildrenList(companyCode, dto.getMenuGroupCode(), roleCodes, dto.getKey());
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(companyCode, roleCodes, children);
			}
						
		}
		
		return list;
	}
	
	private List<MenuHierarchyResponseDTO> getMenuChildrenList(String companyCode, String menuGroupCode, List<String> roleCodes, String parentMenuCode) {					
		
		JPAQuery<MenuHierarchyResponseDTO> query = queryFactory			
				.select(projections(qMenu)).distinct()
				.from(qMenu)
				.innerJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))						
					.and(qMenuRoleMapping.id.roleCode.in(roleCodes))
					)									
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
				      ,qMenu.parentMenuCode.eq(parentMenuCode)
				      );
																		
		return query.fetch();
	}
	
	private QMenuHierarchyResponseDTO projections(QMenu qMenu) {		
				
		return new QMenuHierarchyResponseDTO(							
				qMenu.id.menuCode,
				qMenu.name,
				qMenu.menuGroup.id.menuGroupCode,
				qMenu.parentMenuCode,
				qMenu.type,
				qMenu.sequence,
				qMenu.level,
				qMenu.appUrl);
	}

}
