package com.like.system.systemcode.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeType;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeId;
import com.like.system.systemcode.adapter.out.persistence.jpa.entity.JpaBizCodeTypeMapper;
import com.like.system.systemcode.adapter.out.persistence.jpa.repository.BizCodeTypeJpaRepository;
import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;
import com.like.system.systemcode.application.port.out.BizCodeTypeDeletePort;
import com.like.system.systemcode.application.port.out.BizCodeTypeSavePort;
import com.like.system.systemcode.application.port.out.BizCodeTypeSelectAllPort;
import com.like.system.systemcode.application.port.out.BizCodeTypeSelectPort;
import com.like.system.systemcode.domain.BizCodeType;

@Repository
@Transactional
public class BizCodeTypeAdapter implements BizCodeTypeSelectPort, BizCodeTypeSavePort, BizCodeTypeDeletePort, BizCodeTypeSelectAllPort {

	BizCodeTypeJpaRepository repository;
	
	public BizCodeTypeAdapter(BizCodeTypeJpaRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public BizCodeType select(String companyCode, String typeId) {
		JpaBizCodeType jpaEntity = this.repository.findById(new JpaBizCodeTypeId(companyCode, typeId)).orElse(null);
		
		return JpaBizCodeTypeMapper.toDomainEntity(jpaEntity);
	}
	
	@Override
	public List<BizCodeTypeSaveDTO> select(String companyCode) {
		List<JpaBizCodeType> list = this.repository.findAll();
		return list.stream().map(e -> JpaBizCodeTypeMapper.toDTO(e)).toList();
	}
	
	@Override
	public BizCodeTypeSaveDTO selectDTO(String companyCode, String typeId) {
		JpaBizCodeType jpaEntity = this.repository.findById(new JpaBizCodeTypeId(companyCode, typeId)).orElse(null);
		return JpaBizCodeTypeMapper.toDTO(jpaEntity);
	}
	
	@Override
	public void Save(BizCodeType entity) {
		this.repository.save(JpaBizCodeTypeMapper.toJPAEntity(entity));		
	}
	
	@Override
	public void delete(String companyCode, String typeId) {
		this.repository.deleteById(new JpaBizCodeTypeId(companyCode, typeId));		
	}
	
}
