package com.coz.TodoApp.entity;

import com.coz.TodoApp.dto.TodoDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "todo_table")
public class TodoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private int todoOrder;

    @Column(nullable = false)
    private boolean completed;

    // DTO -> Entity
    public static TodoEntity toTodoEntity(TodoDTO todoDTO) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(todoDTO.getId());
        todoEntity.setTitle(todoDTO.getTitle());
        todoEntity.setTodoOrder(todoDTO.getTodoOrder());
        todoEntity.setCompleted(todoDTO.isCompleted());
        return todoEntity;
    }
}
