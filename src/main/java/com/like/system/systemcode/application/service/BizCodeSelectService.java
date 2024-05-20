package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.in.BizCodeSelectUseCase;
import com.like.system.systemcode.application.port.out.BizCodeSelectPort;
import com.like.system.systemcode.dto.BizCodeSaveDTO;

@Service
public class BizCodeSelectService implements BizCodeSelectUseCase {
	
	BizCodeSelectPort port;
	
	public BizCodeSelectService(BizCodeSelectPort port) {		
		this.port = port;
	}
	
	@Override
	public BizCodeSaveDTO select(String companyCode, String typeId, String code) {						
		return this.port.selectDTO(companyCode, typeId, code);
	}
	
}
