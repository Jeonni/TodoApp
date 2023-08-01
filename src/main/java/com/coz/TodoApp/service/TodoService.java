package com.coz.TodoApp.service;

import com.coz.TodoApp.entity.Todo;
import com.coz.TodoApp.exception.BusinessLogicException;
import com.coz.TodoApp.exception.ExceptionCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // ** Create
    // - Todo 등록
    public Todo addTodo(Todo todo) {
        verifyExistsTitle(todo.getTitle());
        return todoRepository.save(todo);
    }

    private void verifyExistsTitle(String title) {
        Optional<Todo> todo = todoRepository.findByTitle(title);
        if (todo.isPresent()) throw new BusinessLogicException(ExceptionCode.TODO_EXISTS);
    }

    // ** Read
    // - Todo 리스트 조회
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }

    // - Todo 특정 id로 조회
    public Todo getTodoById(Long id) {
        return findVerifiedTodo(id);
    }

    private Todo findVerifiedTodo(Long id) {
        Optional<Todo> optionalTodo = todoRepository.findById(id);
        Todo findTodo = optionalTodo.orElseThrow(() -> new BusinessLogicException(ExceptionCode.TODO_NOT_FOUND));
        return findTodo;
    }


    // ** Update
    // - Todo 완료 표시
    public void completeTodoItem(Long id) {
        Todo todo = todoRepository.findById(id).orElse(null);
        if (todo != null) {
            todo.setCompleted(true);
            todoRepository.save(todo);
        }
    }

    // - Todo 수정
    public Todo updateTodo(Todo todo) {
        Todo findTodo = findVerifiedTodo(todo.getId());
        verifyExistsTitle(todo.getTitle());
        Optional.ofNullable(todo.getTitle()).ifPresent(title -> findTodo.setTitle(title));
        Optional.ofNullable(todo.getTodoOrder()).ifPresent(order -> findTodo.setTodoOrder(order));
        Optional.ofNullable(todo.isCompleted()).ifPresent(completed -> findTodo.setCompleted(completed));

        return todoRepository.save(findTodo);
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
        Todo findTodo = findVerifiedTodo(id);
        todoRepository.delete(findTodo);
    }
}
