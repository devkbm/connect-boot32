package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.in.BizCodeTypeDeleteUseCase;
import com.like.system.systemcode.application.port.out.BizCodeTypeDeletePort;

@Service
public class BizCodeTypeDeleteService implements BizCodeTypeDeleteUseCase {

	BizCodeTypeDeletePort port;
	
	public BizCodeTypeDeleteService(BizCodeTypeDeletePort port) {
		this.port = port;
	}
	
	@Override
	public void delete(String companyCode, String typeId) {
		this.port.delete(companyCode, typeId);		
	}
	
	
}
