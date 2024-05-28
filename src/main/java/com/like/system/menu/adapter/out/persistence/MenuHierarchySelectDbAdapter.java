package com.like.system.menu.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.application.port.out.MenuHierarchySelectDbPort;
import com.like.system.menu.domain.MenuHierarchy;
import com.like.system.menu.domain.QMenu;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuHierarchySelectDbAdapter implements MenuHierarchySelectDbPort {

	JPAQueryFactory queryFactory;
	private final QMenu qMenu = QMenu.menu;
	
	MenuHierarchySelectDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuHierarchy> getAllFlattenNodes(String companyCode, String menuGroupCode) {
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
						))
				.from(qMenu)
				.where(qMenu.id.menuGroupId.companyCode.eq(companyCode)
					  ,qMenu.id.menuGroupId.menuGroupCode.eq(menuGroupCode))
				.orderBy(qMenu.parentMenuCode.asc(), qMenu.sequence.asc())				
				.fetch();
	}

}
