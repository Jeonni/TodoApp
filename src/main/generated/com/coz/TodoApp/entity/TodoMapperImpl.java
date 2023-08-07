package com.coz.TodoApp.entity;

import com.coz.TodoApp.dto.TodoPatchDTO;
import com.coz.TodoApp.dto.TodoPostDTO;
import com.coz.TodoApp.dto.TodoResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T00:22:03+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.8 (Oracle Corporation)"
)
@Component
public class TodoMapperImpl implements TodoMapper {

    @Override
    public Todo todoPostDTOtoTodo(TodoPostDTO todoPostDTO) {
        if ( todoPostDTO == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setCompleted( todoPostDTO.isCompleted() );
        todo.setTitle( todoPostDTO.getTitle() );
        todo.setTodoOrder( todoPostDTO.getTodoOrder() );

        return todo;
    }

    @Override
    public Todo todoPatchDTOtoTodo(TodoPatchDTO todoPatchDTO) {
        if ( todoPatchDTO == null ) {
            return null;
        }

        Todo todo = new Todo();

        todo.setCompleted( todoPatchDTO.isCompleted() );
        todo.setId( todoPatchDTO.getId() );
        todo.setTitle( todoPatchDTO.getTitle() );
        todo.setTodoOrder( todoPatchDTO.getTodoOrder() );

        return todo;
    }

    @Override
    public TodoResponseDTO todoToTodoResponseDTO(Todo todo) {
        if ( todo == null ) {
            return null;
        }

        TodoResponseDTO.TodoResponseDTOBuilder todoResponseDTO = TodoResponseDTO.builder();

        todoResponseDTO.id( todo.getId() );
        todoResponseDTO.title( todo.getTitle() );
        todoResponseDTO.todoOrder( todo.getTodoOrder() );
        todoResponseDTO.completed( todo.isCompleted() );

        return todoResponseDTO.build();
    }

    @Override
    public List<TodoResponseDTO> todoListToTodoResponseDTOList(List<Todo> todoList) {
        if ( todoList == null ) {
            return null;
        }

        List<TodoResponseDTO> list = new ArrayList<TodoResponseDTO>( todoList.size() );
        for ( Todo todo : todoList ) {
            list.add( todoToTodoResponseDTO( todo ) );
        }

        return list;
    }
}
