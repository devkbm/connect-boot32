package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.QMenu;
import com.like.system.menurole.application.port.out.SystemUserMenuHierarchySelect2DbPort;
import com.like.system.menurole.domain.MenuRoleHierarchy;
import com.like.system.menurole.domain.QMenuRoleMapping;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SystemUserMenuHierarchySelect2DbAdapter implements SystemUserMenuHierarchySelect2DbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	SystemUserMenuHierarchySelect2DbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuRoleHierarchy> select(String companyCode, String menuGroupCode, List<String> roleCodes) {		
		return queryFactory
				.select(Projections.fields(MenuRoleHierarchy.class,
						qMenuRoleMapping.id.companyCode,
						qMenuRoleMapping.id.menuGroupCode,
						qMenuRoleMapping.id.menuCode,
						qMenuRoleMapping.id.roleCode,
						qMenu.parentMenuCode
						))
				.from(qMenu)
				.innerJoin(qMenuRoleMapping)
					.on(qMenu.id.menuGroupId.companyCode.eq(qMenuRoleMapping.id.companyCode)
					.and(qMenu.id.menuGroupId.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
					.and(qMenu.id.menuCode.eq(qMenuRoleMapping.id.menuCode))	
					.and(qMenuRoleMapping.id.roleCode.in(roleCodes))
					)
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode)
					  )
				.fetch();
		
	}

}
