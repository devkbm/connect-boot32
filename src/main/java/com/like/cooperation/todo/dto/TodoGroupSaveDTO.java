package com.like.cooperation.todo.dto;

import com.like.cooperation.todo.domain.TodoGroup;

public record TodoGroupSaveDTO(			
		String clientAppUrl,
		String companyCode,
		Long pkTodoGroup,
		String todoGroupName
		) {
	
	public void modifyEntity(TodoGroup entity) {
		entity.modify(todoGroupName);
	}
}