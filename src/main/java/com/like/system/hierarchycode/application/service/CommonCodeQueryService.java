package com.like.system.hierarchycode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.hierarchycode.application.port.in.dto.CodeComboDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeDTO;
import com.like.system.hierarchycode.application.port.in.dto.CodeHierarchy;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CommonCodeQueryRepository;

@Service
@Transactional(readOnly=true)
public class CommonCodeQueryService {

	private CommonCodeQueryRepository codeRepository;
			
	public CommonCodeQueryService(CommonCodeQueryRepository codeRepository) {
		this.codeRepository = codeRepository;
	}
		
	public List<Code> getCodeList(String parentCodeId) {		
		return codeRepository.getCodeList(parentCodeId);
	}
	
	public List<Code> getCodeList(CodeDTO.Search searchCondition) {		
		return codeRepository.getCodeList(searchCondition.getCondition());
	}
	
	public List<CodeHierarchy> getCodeHierarchyList(CodeDTO.Search searchCondition) {		
		
		return codeRepository.getCodeHierarchyList(searchCondition);
	}
	
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return codeRepository.getCodeListByComboBox(codeGroup);
	}	
}
