package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.in.BizCodeTypeSelectUseCase;
import com.like.system.systemcode.application.port.out.BizCodeTypeSelectPort;
import com.like.system.systemcode.dto.BizCodeTypeSaveDTO;

@Service
public class BizCodeTypeSelectService implements BizCodeTypeSelectUseCase {

	BizCodeTypeSelectPort port;
	
	public BizCodeTypeSelectService(BizCodeTypeSelectPort port) {
		this.port = port;
	}

	@Override
	public BizCodeTypeSaveDTO select(String companyCode, String typeId) {
		return this.port.selectDTO(companyCode, typeId);
	}
	
}
