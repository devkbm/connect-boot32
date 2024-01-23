package com.like.system.systemcode.application.port.in;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectUseCase {
	
	BizCodeTypeSaveDTO select(String organizationCode, String typeId);
}
