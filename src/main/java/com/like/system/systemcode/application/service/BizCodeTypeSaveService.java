package com.like.system.systemcode.application.service;

import org.springframework.stereotype.Service;

import com.like.system.systemcode.application.port.in.BizCodeTypeSaveUseCase;
import com.like.system.systemcode.application.port.out.BizCodeTypeSavePort;
import com.like.system.systemcode.dto.BizCodeTypeMapper;
import com.like.system.systemcode.dto.BizCodeTypeSaveDTO;

@Service
public class BizCodeTypeSaveService implements BizCodeTypeSaveUseCase {
	BizCodeTypeSavePort port;

	public BizCodeTypeSaveService(BizCodeTypeSavePort port) {
		this.port = port;
	}
	
	@Override
	public void save(BizCodeTypeSaveDTO dto) {
		this.port.Save(BizCodeTypeMapper.toEntity(dto));		
	}
}
