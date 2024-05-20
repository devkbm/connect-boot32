package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.QMenu;

import com.like.system.menurole.domain.QMenuRoleMapping;
import com.like.system.menurole.dto.MenuRoleMappingHierarchyResponseDTO;
import com.like.system.menurole.dto.QMenuRoleMappingHierarchyResponseDTO;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelectDbPort;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleHierarchyDbAdapter implements MenuRoleHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuRoleHierarchyDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuRoleMappingHierarchyResponseDTO> select(String companyCode, String menuGroupCode, String roleCode) {
		List<MenuRoleMappingHierarchyResponseDTO> rootList = this.getMenuRootList(companyCode, menuGroupCode, roleCode);
		
		List<MenuRoleMappingHierarchyResponseDTO> list = this.getMenuHierarchyDTO(companyCode, roleCode, rootList); 
		
		for ( MenuRoleMappingHierarchyResponseDTO dto : list ) {
			MenuRoleHierarchyHalfChecker.setHalfChecked(dto);
		}
				
		return list;
	}
		
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuRootList(String companyCode, String menuGroupCode, String roleCode) {			
		
		JPAQuery<MenuRoleMappingHierarchyResponseDTO> query = queryFactory
				.select(projections(qMenu, qMenuRoleMapping, roleCode))
				.from(qMenu)
				.leftJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))
					.and(qMenuRoleMapping.id.roleCode.eq(roleCode))
					)
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  ,qMenu.parentMenuCode.isNull()
					  );													
				
		return query.fetch();
	}
	
	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuHierarchyDTO(String companyCode, String roleCode, List<MenuRoleMappingHierarchyResponseDTO> list) {
		List<MenuRoleMappingHierarchyResponseDTO> children = null;
		
		for ( MenuRoleMappingHierarchyResponseDTO dto : list ) {			
			
			children = getMenuChildrenList(companyCode, dto.getMenuGroupCode(), dto.getKey(), roleCode);
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(companyCode, roleCode, children);
			}
						
		}
		
		return list;
	}
	
	private List<MenuRoleMappingHierarchyResponseDTO> getMenuChildrenList(String companyCode, String menuGroupCode, String parentMenuCode, String roleCode) {					
		
		JPAQuery<MenuRoleMappingHierarchyResponseDTO> query = queryFactory			
				.select(projections(qMenu, qMenuRoleMapping, roleCode))
				.from(qMenu)
				.leftJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))						
					.and(qMenuRoleMapping.id.roleCode.eq(roleCode))
					)									
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
				      ,qMenu.parentMenuCode.eq(parentMenuCode)
				      );
																		
		return query.fetch();
	}
	
	private QMenuRoleMappingHierarchyResponseDTO projections(QMenu qMenu, QMenuRoleMapping qMenuRoleMapping, String sroleCode) {		
		
		Expression<Boolean> checked = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(true)
				.otherwise(false)
				.as("checked");
				
		// 저장된 Role Code가 없으면 조회 roleCode로 설정
		Expression<String> roleCode = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(qMenuRoleMapping.id.roleCode)
				.otherwise(sroleCode)
				.as("roleCode");
		
		/*
		 *  
			public MenuRoleMappingHierarchyResponseDTO(
			String key, 
			String title,
			boolean checked,
			String menuGroupCode,
			String menuCode,
			String roleCode) {
		 */
		
		QMenu qMenu2 = new QMenu("menu2");
		QMenu qMenu3 = new QMenu("menu3");
		QMenuRoleMapping qMenuRoleMapping3 = new QMenuRoleMapping("menurole");
		
		return new QMenuRoleMappingHierarchyResponseDTO(							
				qMenu.id.menuCode,
				qMenu.name,
				checked,
				qMenu.id.menuGroupId.menuGroupCode,
				qMenu.id.menuCode,
				roleCode,
				// 하위 메뉴 건수
			    ExpressionUtils.as(
			    		JPAExpressions.select(qMenu2.id.count())
			    					  .from(qMenu2)
			    					  .where(qMenu2.id.menuGroupId.companyCode.eq(qMenu.id.menuGroupId.companyCode)
			    							,qMenu2.id.menuGroupId.menuGroupCode.eq(qMenu.id.menuGroupId.menuGroupCode)	 
                                	        ,qMenu2.parentMenuCode.eq(qMenu.id.menuCode)),			    	
                        "menuChildrenCount"),
			    // 선택된 하위 메뉴롤 건수
			    ExpressionUtils.as(
			    		JPAExpressions.select(qMenuRoleMapping3.id.count())
			    					  .from(qMenu3)
			    					  .innerJoin(qMenuRoleMapping3)
			    					  .on(qMenu3.id.menuGroupId.companyCode.eq(qMenuRoleMapping3.id.companyCode)
			    						.and(qMenu3.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping3.id.menuGroupCode))
			    						.and(qMenu3.id.menuCode.eq(qMenuRoleMapping3.id.menuCode))						
			    						.and(qMenuRoleMapping3.id.roleCode.eq(qMenuRoleMapping3.id.roleCode))
			    						.and(qMenuRoleMapping3.id.roleCode.eq(qMenuRoleMapping.id.roleCode))
			    						)
			    					  .where(qMenu3.id.menuGroupId.companyCode.eq(qMenu.id.menuGroupId.companyCode)
			    							,qMenu3.id.menuGroupId.menuGroupCode.eq(qMenu.id.menuGroupId.menuGroupCode)	 
                                	        ,qMenu3.parentMenuCode.eq(qMenu.id.menuCode)),			    	
                        "menuRoleChildrenCount")
			     );
	}

}
