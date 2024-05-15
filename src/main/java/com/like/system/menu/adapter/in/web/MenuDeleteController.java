package com.like.system.menu.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.like.core.message.MessageUtil;
import com.like.system.menu.application.port.in.MenuDeleteUseCase;

@RestController
public class MenuDeleteController {
	
	private MenuDeleteUseCase useCase;		
			
	public MenuDeleteController(MenuDeleteUseCase useCase) {
		this.useCase = useCase;		
	}					
	
	@DeleteMapping("/api/system/menugroup/{menuGroupCode}menu/{menuCode}")
	public ResponseEntity<?> delMenu(@RequestParam String companyCode
									,@PathVariable String menuGroupCode
									,@PathVariable String menuCode) {				
												
		useCase.delete(companyCode, menuGroupCode, menuCode);							
		
		return toList(null, MessageUtil.getDeleteMessage(1));
	}	
	
}
