package com.like.system.systemcode.application.port.in;

import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;

public interface BizCodeSelectUseCase {
	BizCodeSaveDTO select(String organizationCode, String typeId, String code);	
}
