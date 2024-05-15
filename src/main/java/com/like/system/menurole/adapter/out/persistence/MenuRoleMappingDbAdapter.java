package com.like.system.menurole.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.like.system.menurole.domain.QMenuRoleMapping;
import com.like.system.menurole.adapter.out.persistence.jpa.repository.MenuRoleMappingJpaRepository;
import com.like.system.menurole.application.port.out.MenuRoleMappingSaveDbPort;
import com.like.system.menurole.domain.MenuRoleMapping;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MenuRoleMappingDbAdapter implements MenuRoleMappingSaveDbPort {

	JPAQueryFactory queryFactory;
	MenuRoleMappingJpaRepository repository;
	private final QMenuRoleMapping qMenuRoleMapping = QMenuRoleMapping.menuRoleMapping;
	
	MenuRoleMappingDbAdapter(MenuRoleMappingJpaRepository repository
							,JPAQueryFactory queryFactory) {
		this.repository = repository;
		this.queryFactory = queryFactory;
	}
	
	@Override	
	public void save(List<MenuRoleMapping> entityList) {									
		this.repository.saveAll(entityList);		
	}

	@Override
	public void clear(String orgnizationCode, String menuGroupCode, String roleCode) {
		this.queryFactory.delete(qMenuRoleMapping)						 						 
						 .where(qMenuRoleMapping.id.companyCode.eq(orgnizationCode)
							   ,qMenuRoleMapping.id.menuGroupCode.eq(menuGroupCode) 
							   ,qMenuRoleMapping.id.roleCode.eq(roleCode))												
						 .execute();
	}

}
