package com.like.hrm.hrmcode.application.service;

import static org.springframework.util.StringUtils.hasText;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.hrm.hrmcode.domain.HrmCodeType;
import com.like.hrm.hrmcode.adapter.out.persistence.HrmCodeRepository;
import com.like.hrm.hrmcode.adapter.out.persistence.HrmCodeTypeRepository;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeSaveDTO;
import com.like.hrm.hrmcode.domain.HrmCode;
import com.like.hrm.hrmcode.domain.HrmCodeId;

@Service
@Transactional
public class HrmTypeService {

	private HrmCodeTypeRepository hrmTypeRepository;
	private HrmCodeRepository hrmTypeDetailCodeRepository;	
	
	public HrmTypeService(HrmCodeTypeRepository hrmTypeRepository
						 ,HrmCodeRepository hrmTypeDetailCodeRepository) {		
		this.hrmTypeRepository = hrmTypeRepository;
		this.hrmTypeDetailCodeRepository = hrmTypeDetailCodeRepository;
	}					
	
	public HrmCodeType getHrmType(String id) {
		return hrmTypeRepository.findById(id).orElse(null);
	}
	
	public HrmCodeTypeSaveDTO getHrmTypeDTO(String code) {
		HrmCodeType entity = this.getHrmType(code);
		
		return HrmCodeTypeSaveDTO.convert(entity);
	}
	
	public void saveHrmType(HrmCodeTypeSaveDTO dto) {
		HrmCodeType hrmType = dto.typeId() == null ? null : hrmTypeRepository.findById(dto.typeId()).orElse(null);			
		
		if (hrmType == null) {
			hrmType = dto.newEntity();
		} else {					
			hrmType = dto.modify(hrmType);
		}
		
		hrmTypeRepository.save(hrmType);		
	}	

	public void deleteHrmType(String id) {
		hrmTypeRepository.deleteById(id);				
	}
	
	public HrmCode getTypeDetailCode(HrmCodeId id) {
		return hrmTypeDetailCodeRepository.findById(id).orElse(null);
	}
	
	public HrmCodeSaveDTO getTypeDetailCodeDTO(HrmCodeId id) {
		
		HrmCode entity = hrmTypeDetailCodeRepository.findById(id).orElse(null);				
		
		return HrmCodeSaveDTO.convert(entity);
	}
	
	public void saveTypeDetailCode(HrmCodeSaveDTO dto) {		
		HrmCode typeDetailCode = null;
			
		if (hasText(dto.typeId()) && hasText(dto.code())) {
			typeDetailCode = this.getTypeDetailCode(new HrmCodeId(dto.typeId(), dto.code()));
		}
			
		if (typeDetailCode == null) {
			typeDetailCode = dto.newEntity();
		} else {
			typeDetailCode = dto.modify(typeDetailCode);
		}
		
		hrmTypeDetailCodeRepository.save(typeDetailCode);
	}
	
	public void deleteTypeDetailCode(HrmCodeId id) {
		hrmTypeDetailCodeRepository.deleteById(id);
	}		
	
}
