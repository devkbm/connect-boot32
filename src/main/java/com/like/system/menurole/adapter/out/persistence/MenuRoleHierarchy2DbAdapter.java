package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.QMenu;
import com.like.system.menurole.application.port.out.MenuRoleHierarchySelect2DbPort;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.like.system.menurole.domain.QMenuRoleMapping;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleHierarchy2DbAdapter implements MenuRoleHierarchySelect2DbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuRoleHierarchy2DbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;		
	}
	
	@Override
	public List<MenuRoleHierarchy> select(String companyCode, String menuGroupCode, String roleCode) {
		Expression<Boolean> checked = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(true)
				.otherwise(false)
				.as("checked");
				
		// 저장된 Role Code가 없으면 조회 roleCode로 설정
		Expression<String> roleCodeColumn = new CaseBuilder()
				.when(qMenuRoleMapping.id.roleCode.isNotNull()).then(qMenuRoleMapping.id.roleCode)
				.otherwise(roleCode)
				.as("roleCode");		
		
		return queryFactory
				.select(Projections.fields(MenuRoleHierarchy.class,
						qMenu.id.menuGroupId.companyCode,
						qMenu.id.menuGroupId.menuGroupCode,
						qMenu.id.menuCode,
						roleCodeColumn,
						checked,
						qMenu.parentMenuCode
						))
				.from(qMenu)
				.leftJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))	
					.and(qMenuRoleMapping.id.roleCode.eq(roleCode))
					)
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  )
				.fetch();
	}

}
