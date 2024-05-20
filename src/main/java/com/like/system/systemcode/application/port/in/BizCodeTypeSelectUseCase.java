package com.like.system.systemcode.application.port.in;

import com.like.system.systemcode.dto.BizCodeTypeSaveDTO;

public interface BizCodeTypeSelectUseCase {
	
	BizCodeTypeSaveDTO select(String companyCode, String typeId);
}
