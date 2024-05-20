package com.like.cooperation.todo.application.port.in;

import com.like.cooperation.todo.dto.TodoSaveDTO;

public interface TodoSaveUseCase {
	TodoSaveDTO save(TodoSaveDTO dto);
}
