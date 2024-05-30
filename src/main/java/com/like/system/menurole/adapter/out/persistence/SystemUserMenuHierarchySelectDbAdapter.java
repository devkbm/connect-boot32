package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.MenuHierarchy;
import com.like.system.menu.domain.QMenu;
import com.like.system.menurole.application.port.out.SystemUserMenuHierarchySelectDbPort;
import com.like.system.menurole.domain.QMenuRoleMapping;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class SystemUserMenuHierarchySelectDbAdapter implements SystemUserMenuHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	SystemUserMenuHierarchySelectDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuHierarchy> select(String companyCode, String menuGroupCode, List<String> roleCodes) {		
		return queryFactory
				.select(Projections.fields(MenuHierarchy.class,
						qMenu.id.menuGroupId.companyCode,
						qMenu.id.menuGroupId.menuGroupCode,
						qMenu.id.menuCode,
						qMenu.name.as("menuName"),
						qMenu.type.as("menuType"),
						qMenu.appUrl,
						qMenu.sequence,
						qMenu.level,
						qMenu.parentMenuCode
						)).distinct()
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
