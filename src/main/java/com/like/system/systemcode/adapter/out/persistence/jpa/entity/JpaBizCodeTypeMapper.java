package com.like.system.systemcode.adapter.out.persistence.jpa.entity;

import com.like.system.systemcode.domain.BizCodeType;
import com.like.system.systemcode.domain.BizTypeEnum;
import com.like.system.systemcode.dto.BizCodeTypeSaveDTO;

public class JpaBizCodeTypeMapper {

	public static BizCodeTypeSaveDTO toDTO(JpaBizCodeType jpaEntity) {
		if (jpaEntity == null) return null;
		
		return BizCodeTypeSaveDTO.builder()
								 .companyCode(jpaEntity.getId().getCompanyCode())
								 .typeId(jpaEntity.getId().getTypeId())
								 .typeName(jpaEntity.getName())
								 .sequence(jpaEntity.getSequence())
								 .bizType(jpaEntity.getBizType().toString())
								 .comment(jpaEntity.getComment())
								 .build();
	}
	
	public static BizCodeType toDomainEntity(JpaBizCodeType jpaEntity) {
		if (jpaEntity == null) return null;
							
		return BizCodeType.builder()
						  .companyCode(jpaEntity.getId().getCompanyCode())
						  .typeId(jpaEntity.getId().getTypeId())
						  .name(jpaEntity.getName())
						  .bizTypeEnum(BizTypeEnum.valueOf(jpaEntity.getBizType().toString()))
						  .comment(jpaEntity.getComment())
						  .build();
	}
	
	public static JpaBizCodeType toJPAEntity(BizCodeType entity) {
		if (entity == null) return null;
		
		return JpaBizCodeType.builder()
							 .companyCode(entity.getId().getCompanyCode())
							 .typeId(entity.getId().getTypeId())
							 .name(entity.getName())
							 .bizType(JpaBizTypeEnum.valueOf(entity.getBizType().toString()))
							 .comment(entity.getComment())
							 .build();				
	}
	
}
