package com.like.system.systemcode.adapter.out.persistence.jpa.entity;

import com.like.system.systemcode.domain.BizCode;
import com.like.system.systemcode.domain.BizCodeType;
import com.like.system.systemcode.dto.BizCodeSaveDTO;

public class JpaBizCodeMapper {

	public static BizCodeSaveDTO toDTO(JpaBizCode jpaEntity) {		
		
		return BizCodeSaveDTO.builder()
							 .companyCode(jpaEntity.getId().getBizCodeTypeId().getCompanyCode())
							 .typeId(jpaEntity.getId().getBizCodeTypeId().getTypeId())
							 .code(jpaEntity.getId().getCode())
							 .codeName(jpaEntity.getCodeName())
							 .useYn(jpaEntity.getUseYn())
							 .sequence(jpaEntity.getSequence())
							 .comment(jpaEntity.getComment())
							 .build();		
	}
	
	public static BizCode toDomainEntity(JpaBizCode jpaEntity, BizCodeType bizCodeType) {
		
		return BizCode.builder()
					  .bizCodeType(bizCodeType)
					  .code(jpaEntity.getId().getCode())
					  .useYn(jpaEntity.getUseYn())
					  .name(jpaEntity.getCodeName())
					  .sequence(jpaEntity.getSequence())
					  .comment(jpaEntity.getComment())
				      .build();
	}
	
	public static JpaBizCode toJPAEntity(BizCode entity, JpaBizCodeType jpaBizCodeType) {
		
		return JpaBizCode.builder()
						 .bizType(jpaBizCodeType)
						 .code(entity.getId().getCode())
						 .name(entity.getCodeName())
						 .useYn(entity.getUseYn())
						 .sequence(entity.getSequence())
						 .comment(entity.getComment())
						 .build();
	}
}
