package com.coz.TodoApp.service;

import com.coz.TodoApp.dto.TodoDTO;
import com.coz.TodoApp.entity.TodoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // ** Create
    // - Todo 등록
    public TodoDTO addTodo(TodoDTO todoDTO) {
        TodoEntity todoEntity = TodoEntity.toTodoEntity(todoDTO);
        TodoEntity savedTodo = todoRepository.save(todoEntity);
        return TodoDTO.toTodoDTO(savedTodo);
    }

    // ** Read
    // - Todo 리스트 조회
    public List<TodoDTO> getAllTodos() {
        List<TodoEntity> todoEntityList = todoRepository.findAll();
        return todoEntityList.stream()
                .map(TodoDTO::toTodoDTO)
                .collect(Collectors.toList());
    }

    // - Todo 특정 id로 조회
    public TodoDTO getTodoById(Long id) {
        Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
        return todoEntityOptional.map(TodoDTO::toTodoDTO).orElse(null);
    }

    // ** Update
    // - Todo 완료 표시
    public void completeTodoItem(Long id) {
        TodoEntity todoEntity = todoRepository.findById(id).orElse(null);
        if (todoEntity != null) {
            todoEntity.setCompleted(true);
            todoRepository.save(todoEntity);
        }
    }

    // - Todo 수정
    public TodoDTO updateTodo(Long id, TodoDTO todoDTO) {
        Optional<TodoEntity> todoEntityOptional = todoRepository.findById(id);
        if (todoEntityOptional.isPresent()) {
            TodoEntity todoEntity = todoEntityOptional.get();
            todoEntity.setTitle(todoDTO.getTitle());
            todoEntity.setCompleted(todoEntity.isCompleted());
            TodoEntity updatedTodo = todoRepository.save(todoEntity);
            return TodoDTO.toTodoDTO(updatedTodo);
        }
        return null;
    }


    // ** Delete
    // - Todo 삭제
    public boolean deleteAllTodos() {
        try {
            todoRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // - Todo 특정 id로 삭제
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }
}
