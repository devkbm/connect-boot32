package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.dto.MenuHierarchyResponseDTO;
import com.like.system.menu.application.port.dto.QMenuHierarchyResponseDTO;
import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;
import com.like.system.menu.domain.QMenu;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuHierarchyDbAdapter implements MenuHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	
	MenuHierarchyDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuHierarchyResponseDTO> select(String companyCode, String menuGroupCode) {
				
		List<MenuHierarchyResponseDTO> rootList = this.getMenuRootList(companyCode, menuGroupCode);
		
		return this.getMenuHierarchyDTO(companyCode, rootList);
	}
	
	private List<MenuHierarchyResponseDTO> getMenuRootList(String companyCode, String menuGroupCode) {			
		
		// menuGroupCode, companyCode 반대로 동작 확인 필요
		JPAQuery<MenuHierarchyResponseDTO> query = queryFactory
				.select(projections(qMenu))
				.from(qMenu)								
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  ,qMenu.parentMenuCode.isNull()
					  );													
				
		return query.fetch();
	}
	
	// TODO 계층 쿼리 테스트해보아야함 1 루트 노드 검색 : getMenuChildrenList 2. 하위노드 검색 : getMenuHierarchyDTO
	private List<MenuHierarchyResponseDTO> getMenuHierarchyDTO(String companyCode, List<MenuHierarchyResponseDTO> list) {
		List<MenuHierarchyResponseDTO> children = null;
		
		for ( MenuHierarchyResponseDTO dto : list ) {			
			
			children = getMenuChildrenList(companyCode, dto.getMenuGroupCode(), dto.getKey());
			
			if (children.isEmpty()) {
				dto.setIsLeaf(true);
				continue;
			} else {
				dto.setChildren(children);
				dto.setIsLeaf(false);
				
				// 재귀호출
				getMenuHierarchyDTO(companyCode, children);
			}
						
		}
		
		return list;
	}
	
	private List<MenuHierarchyResponseDTO> getMenuChildrenList(String companyCode, String menuGroupCode, String parentMenuCode) {					
		/*
		Expression<Boolean> isLeaf = new CaseBuilder()										
											.when(qMenu.parent.menuCode.isNotNull()).then(true)
											.otherwise(false).as("isLeaf");
		*/
		// menuGroupCode, companyCode 반대로 동작 확인 필요
		JPAQuery<MenuHierarchyResponseDTO> query = queryFactory			
				.select(projections(qMenu))
				.from(qMenu)									
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
				      ,qMenu.parentMenuCode.eq(parentMenuCode)
				      );
																		
		return query.fetch();
	}
	
	private QMenuHierarchyResponseDTO projections(QMenu qMenu) {		
		
		/*
		 *  
		  	public MenuHierarchyResponseDTO(String key, String title, String menuGroupCode, String parentMenuCode,
			MenuType menuType, Long sequence, Long level, String url) {			
		 */
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
