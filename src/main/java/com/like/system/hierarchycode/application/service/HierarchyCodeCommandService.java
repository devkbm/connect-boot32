package com.like.system.hierarchycode.application.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.hierarchycode.application.dto.HierarchySaveDTO;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CodeId;
import com.like.system.hierarchycode.domain.CommonCodeRepository;

@Service
@Transactional
public class HierarchyCodeCommandService {

	private CommonCodeRepository codeRepository;
					
	public HierarchyCodeCommandService(CommonCodeRepository codeRepository) {
		this.codeRepository = codeRepository;
	}
	
	public Code getCode(String systemTypeCode, String code) {				
		return codeRepository.findById(new CodeId(systemTypeCode, code)).orElse(null);
	}

	public void saveCode(Code code) {		
		codeRepository.save(code);		
	}
	
	public void saveCode(HierarchySaveDTO dto) {
		Code parentCode = findParentCode(dto); 
		Code code = findCode(dto);								
		
		if (code == null) {
			code = dto.newCode(parentCode);
		} else {
			dto.modifyCode(code);
		}
				
		codeRepository.save(code);		
	}

	public void deleteCode(String systemTypeCode, String code) {
		codeRepository.deleteById(new CodeId(systemTypeCode, code));		
	}
	
	private Code findCode(HierarchySaveDTO dto) {
		if (dto.codeId() == null) return null;
		
		return codeRepository.findById(new CodeId(dto.systemTypeCode(), dto.codeId())).orElse(null);
	}
	
	private Code findParentCode(HierarchySaveDTO dto) {
		if (dto.parentId() == null) return null;
		
		return codeRepository.findById(new CodeId(dto.systemTypeCode(), dto.parentId())).orElse(null);
	}
		
}
