package com.like.cooperation.todo.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.like.cooperation.todo.domain.TodoGroup;

@JsonIgnoreProperties(ignoreUnknown = true)
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