package com.coz.TodoApp.dto;

import com.coz.TodoApp.entity.TodoEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private int todoOrder;
    private boolean completed;

    // Entity -> DTO
    public static TodoDTO toTodoDTO(TodoEntity todoEntity) {
        TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(todoEntity.getId());
        todoDTO.setTitle(todoEntity.getTitle());
        todoDTO.setTodoOrder(todoEntity.getTodoOrder());
        todoDTO.setCompleted(todoEntity.isCompleted());
        return todoDTO;
    }
}
