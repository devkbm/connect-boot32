package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menu.domain.MenuGroup;
import com.like.system.menu.domain.QMenuGroup;
import com.like.system.menurole.application.port.out.MenuGroupByRolesSelectDbPort;
import com.like.system.menurole.domain.QMenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuGroupByRolesSelectDbAdapter implements MenuGroupByRolesSelectDbPort {

	JPAQueryFactory queryFactory;	
	private final QMenuGroup qMenuGroup = QMenuGroup.menuGroup;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuGroupByRolesSelectDbAdapter(JPAQueryFactory queryFactory) {
		this.queryFactory = queryFactory;
	}
	
	@Override
	public List<MenuGroup> select(String companyCode, List<String> roleCodes) {		
		return this.queryFactory.select(qMenuGroup).distinct()
				                .from(qMenuGroup)
				                .innerJoin(qMenuRoleMapping)
				                .on(qMenuGroup.id.companyCode.eq(qMenuRoleMapping.id.companyCode),
				                	qMenuGroup.id.menuGroupCode.eq(qMenuRoleMapping.id.menuGroupCode))
				                .where(qMenuRoleMapping.id.roleCode.in(roleCodes))
				                .fetch();				               
	}

}
