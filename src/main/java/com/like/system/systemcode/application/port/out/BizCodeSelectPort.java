package com.like.system.systemcode.application.port.out;

import com.like.system.systemcode.domain.BizCode;
import com.like.system.systemcode.dto.BizCodeSaveDTO;

public interface BizCodeSelectPort {
	BizCode select(String companyCode, String typeId, String code);
	
	BizCodeSaveDTO selectDTO(String companyCode, String typeId, String code);
}
