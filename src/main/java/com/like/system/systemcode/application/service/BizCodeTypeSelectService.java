package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeTypeSelectUseCase;
import com.like.system.systemcode.application.port.out.BizCodeTypeSelectPort;

@Service
public class BizCodeTypeSelectService implements BizCodeTypeSelectUseCase {

	BizCodeTypeSelectPort port;
	
	public BizCodeTypeSelectService(BizCodeTypeSelectPort port) {
		this.port = port;
	}

	@Override
	public BizCodeTypeSaveDTO select(String organizationCode, String typeId) {
		return this.port.selectDTO(organizationCode, typeId);
	}
	
}
