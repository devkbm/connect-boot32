package com.like.system.systemcode.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toOne;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.systemcode.application.port.dto.BizCodeTypeSaveDTO;
import com.like.system.systemcode.application.port.in.BizCodeTypeSelectUseCase;

@RestController
public class BizCodeTypeSelectController {

	private BizCodeTypeSelectUseCase useCase;
	
	public BizCodeTypeSelectController(BizCodeTypeSelectUseCase useCase) {
		this.useCase = useCase;
	}	
	
	@GetMapping("/api/system/bizcodetype/{typeId}")
	public ResponseEntity<?> getBizCodeType(@RequestParam String companyCode
										   ,@PathVariable String typeId) {
		
		BizCodeTypeSaveDTO dto = useCase.select(companyCode, typeId);
					
		return toOne(dto, MessageUtil.getQueryMessage(dto == null ? 0 : 1));
	}			
}
