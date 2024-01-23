package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeSelectUseCase;
import com.like.system.systemcode.application.port.out.BizCodeSelectPort;

@Service
public class BizCodeSelectService implements BizCodeSelectUseCase {
	
	BizCodeSelectPort port;
	
	public BizCodeSelectService(BizCodeSelectPort port) {		
		this.port = port;
	}
	
	@Override
	public BizCodeSaveDTO select(String organizationCode, String typeId, String code) {						
		return this.port.selectDTO(organizationCode, typeId, code);
	}
	
}
