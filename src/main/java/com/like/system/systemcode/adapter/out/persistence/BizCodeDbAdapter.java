package com.like.system.systemcode.adapter.out.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCode;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeId;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeMapper;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeId;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeMapper;
import com.like.system.systemcode.adapter.out.persistence.jpa.repository.BizCodeRepository;
import com.like.system.systemcode.adapter.out.persistence.jpa.repository.BizCodeTypeJpaRepository;
import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;
import com.like.system.systemcode.application.port.out.BizCodeDeletePort;
import com.like.system.systemcode.application.port.out.BizCodeSavePort;
import com.like.system.systemcode.application.port.out.BizCodeSelectPort;
import com.like.system.systemcode.domain.BizCode;

@Repository
@Transactional
public class BizCodeDbAdapter implements BizCodeSelectPort, BizCodeSavePort, BizCodeDeletePort {
	
	BizCodeRepository repository;
	BizCodeTypeJpaRepository bizCodeTypeJpaRepository;
	
	public BizCodeDbAdapter(BizCodeRepository repository, BizCodeTypeJpaRepository bizCodeTypeJpaRepository) {
		this.repository = repository;
		this.bizCodeTypeJpaRepository = bizCodeTypeJpaRepository;
	}
	
	@Override
	public BizCode select(String companyCode, String typeId, String code) {
		JpaBizCodeType jpaBizCodeType = bizCodeTypeJpaRepository.findById(new JpaBizCodeTypeId(companyCode, typeId)).orElse(null);
		JpaBizCode jpaEntity = this.repository.findById(new JpaBizCodeId(companyCode, typeId, code)).orElse(null);
		
		return JpaBizCodeMapper.toDomainEntity(jpaEntity, JpaBizCodeTypeMapper.toDomainEntity(jpaBizCodeType));
	}

	@Override
	public BizCodeSaveDTO selectDTO(String companyCode, String typeId, String code) {
		JpaBizCode jpaEntity = this.repository.findById(new JpaBizCodeId(companyCode, typeId, code)).orElse(null);
				
		return JpaBizCodeMapper.toDTO(jpaEntity);
	}
	
	@Override
	public void save(BizCode entity) {
		JpaBizCodeType jpaBizCodeType = bizCodeTypeJpaRepository.findById(new JpaBizCodeTypeId(entity.getId().getBizCodeTypeId().getCompanyCode()
																							  ,entity.getId().getBizCodeTypeId().getTypeId())).orElse(null);
		
		this.repository.save(JpaBizCodeMapper.toJPAEntity(entity, jpaBizCodeType));		
	}
	
	@Override
	public void delete(String companyCode, String typeId, String code) {
		this.repository.deleteById(new JpaBizCodeId(companyCode, typeId, code));		
	}

}
