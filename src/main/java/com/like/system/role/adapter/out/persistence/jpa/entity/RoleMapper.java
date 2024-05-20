package com.like.system.role.adapter.out.persistence.jpa.entity;

import static org.springframework.util.StringUtils.hasText;

import com.like.system.role.domain.Role;
import com.like.system.role.dto.RoleQueryDTO;
import com.querydsl.core.BooleanBuilder;

public class RoleMapper {

	public static JpaRole toJpaEntity(Role entity) {
		return new JpaRole(entity.getCompanyCode()
						  ,entity.getRoleCode()
						  ,entity.getDescription());
	}
	
	public static Role toEntity(JpaRole jpaEntity) {
		if (jpaEntity == null) return null; 
		
		return new Role(jpaEntity.getCompanyCode()
					   ,jpaEntity.getRoleCode()
					   ,""
					   ,jpaEntity.getDescription());			
	}
	
	public static BooleanBuilder toPredicate(RoleQueryDTO dto) {
		BooleanBuilder builder = new BooleanBuilder();
		QJpaRole qType = QJpaRole.jpaRole;
			
		builder.and(qType.id.companyCode.eq(dto.companyCode()));
		
		if (hasText(dto.roleCode())) {
			builder.and(qType.id.roleCode.like("%"+dto.roleCode()+"%"));
		}
		
		if (hasText(dto.description())) {
			builder.and(qType.description.like("%"+dto.description()+"%"));
		}			   	
		
		return builder;
	}
}
