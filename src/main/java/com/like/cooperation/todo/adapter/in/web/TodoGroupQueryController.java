package com.like.cooperation.todo.adapter.in.web;

import static com.like.core.web.util.ResponseEntityUtil.toList;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.like.cooperation.todo.application.port.in.TodoGroupQueryUseCase;
import com.like.cooperation.todo.domain.TodoGroup;
import com.like.core.message.MessageUtil;
import com.like.core.util.SessionUtil;

@RestController
public class TodoGroupQueryController {

	private TodoGroupQueryUseCase useCase;
	
	public TodoGroupQueryController(TodoGroupQueryUseCase useCase) {
		this.useCase = useCase;
	}
	
	@GetMapping("/api/todo/group/mylist")
	public ResponseEntity<?> getMyTodoGroupList() {		
		
		List<TodoGroup> list = useCase.select(SessionUtil.getUserId());			 					
		
		return toList(list, MessageUtil.getQueryMessage(list.size()));
	}
	
}
