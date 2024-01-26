package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.dto.BizCodeMapper;
import com.like.system.systemcode.application.port.dto.BizCodeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeSaveUseCase;
import com.like.system.systemcode.application.port.out.BizCodeSavePort;
import com.like.system.systemcode.application.port.out.BizCodeTypeSelectPort;
import com.like.system.systemcode.domain.BizCodeType;

@Service
public class BizCodeSaveService implements BizCodeSaveUseCase {

	BizCodeSavePort port;
	BizCodeTypeSelectPort bizCodeTypeSelectPort;
	
	public BizCodeSaveService(BizCodeSavePort port, BizCodeTypeSelectPort bizCodeTypeSelectPort) {	
		this.port = port;
		this.bizCodeTypeSelectPort = bizCodeTypeSelectPort;
	}
	
	@Override
	public void save(BizCodeSaveDTO dto) {
		BizCodeType bizCodeType = bizCodeTypeSelectPort.select(dto.companyCode(), dto.typeId());
		this.port.save(BizCodeMapper.toEntity(dto, bizCodeType));
	}
	
}
