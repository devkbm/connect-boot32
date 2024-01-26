package com.like.system.systemcode.application.port.out;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;
import com.like.system.systemcode.domain.BizCodeType;

public interface BizCodeTypeSelectPort {
	BizCodeType select(String companyCode, String typeId);
	
	BizCodeTypeSaveDTO selectDTO(String companyCode, String typeId);	
}
