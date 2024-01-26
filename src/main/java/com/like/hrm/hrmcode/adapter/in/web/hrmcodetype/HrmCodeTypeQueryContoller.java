package com.like.hrm.hrmcode.adapter.in.web.hrmcodetype;

import static com.like.system.core.web.util.ResponseEntityUtil.toList;
import static com.like.system.core.web.util.ResponseEntityUtil.toMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeSaveDTO;
import com.like.hrm.hrmcode.application.port.in.hrmcodetype.HrmCodeTypeQueryUseCase;
import com.like.hrm.hrmcode.application.port.dto.HrmCodeTypeQueryDTO;
import com.like.system.core.message.MessageUtil;

@RestController
public class HrmCodeTypeQueryContoller {

	HrmCodeTypeQueryUseCase useCase;
	
	public HrmCodeTypeQueryContoller(HrmCodeTypeQueryUseCase useCase) {
		this.useCase = useCase;
	}	
	
	@GetMapping("/api/hrm/hrmtype")
	public ResponseEntity<?> getHrmTypeList(HrmCodeTypeQueryDTO dto) {														
		
		List<HrmCodeTypeSaveDTO> list = useCase.select(dto);							
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
	@GetMapping("/api/hrm/hrmtype/test")
	public ResponseEntity<?> getHrmTypeList() {														
		/*
		 String companyCode,
		String clientAppUrl,
		String typeId,
		String typeName,			
		Integer sequence,
		String comment
		 */			
		Map<String, List<HrmCodeTypeSaveDTO>> list = new HashMap<>();
		
		list.put("a", List.of(new HrmCodeTypeSaveDTO("001","1","2","3",4,"5"),
							  new HrmCodeTypeSaveDTO("001","1","2","3",4,"5")));
		list.put("b", List.of(new HrmCodeTypeSaveDTO("001","1","2","3",4,"5"),
				  			  new HrmCodeTypeSaveDTO("001","1","2","3",4,"5")));
									
		return toMap(list, "0");
	}
	
}
