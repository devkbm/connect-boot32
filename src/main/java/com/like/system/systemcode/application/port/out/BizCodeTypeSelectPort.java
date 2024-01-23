package com.like.system.systemcode.application.port.out;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;
import com.like.system.systemcode.domain.BizCodeType;

public interface BizCodeTypeSelectPort {
	BizCodeType select(String organizationCode, String typeId);
	
	BizCodeTypeSaveDTO selectDTO(String organizationCode, String typeId);	
}
