package com.coz.TodoApp.entity;

import com.coz.TodoApp.dto.TodoPatchDTO;
import com.coz.TodoApp.dto.TodoPostDTO;
import com.coz.TodoApp.dto.TodoResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todo todoPostDTOtoTodo(TodoPostDTO todoPostDTO);

    Todo todoPatchDTOtoTodo(TodoPatchDTO todoPatchDTO);

    TodoResponseDTO todoToTodoResponseDTO(Todo todo);

    List<TodoResponseDTO> todoListToTodoResponseDTOList(List<Todo> todoList);
}
