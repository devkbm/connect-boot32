package com.like.system.hierarchycode.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.like.system.hierarchycode.application.dto.CodeComboDTO;
import com.like.system.hierarchycode.application.dto.HierarchyCodeQueryDTO;
import com.like.system.hierarchycode.domain.Code;
import com.like.system.hierarchycode.domain.CommonCodeQueryRepository;

@Service
@Transactional(readOnly=true)
public class HierarchyCodeQueryService {

	private CommonCodeQueryRepository codeRepository;
			
	public HierarchyCodeQueryService(CommonCodeQueryRepository codeRepository) {
		this.codeRepository = codeRepository;
	}
		
	public List<Code> getCodeList(String parentCodeId) {		
		return codeRepository.getCodeList(parentCodeId);
	}
	
	public List<Code> getCodeList(HierarchyCodeQueryDTO searchCondition) {		
		return codeRepository.getCodeList(searchCondition.getCondition());
	}	
	
	public List<CodeComboDTO> getCodeListByComboBox(String codeGroup) {
		return codeRepository.getCodeListByComboBox(codeGroup);
	}	
}
