package com.coz.TodoApp.controller;

import com.coz.TodoApp.dto.TodoDTO;
import com.coz.TodoApp.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // ** Creat
    // - Todo 등록
    @PostMapping
    public ResponseEntity<TodoDTO> addTodo(@RequestBody TodoDTO todoDTO) {
        TodoDTO addedTodo = todoService.addTodo(todoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTodo);
    }

    // ** Read
    // - Todo 리스트 조회
    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos() {
        List<TodoDTO> todos = todoService.getAllTodos();
        return ResponseEntity.ok(todos);
    }

    // - Todo 특정 id로 조회
    @GetMapping("{id}")
    public ResponseEntity<TodoDTO> getTodoById(@PathVariable Long id) {
        TodoDTO todoDTO = todoService.getTodoById(id);
        if (todoDTO != null) {
            return ResponseEntity.ok(todoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ** Update
    // - Todo 완료 표시
    @PutMapping("/{id}/complete")
    public void completeTodoItem(@PathVariable Long id) {
        todoService.completeTodoItem(id);
    }

    // - Todo 수정
    @PatchMapping("/{id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO) {
        TodoDTO updatedTodo = todoService.updateTodo(id, todoDTO);
        if (updatedTodo != null) {
            return ResponseEntity.ok(updatedTodo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ** Delete
    // - Todo 삭제
    @DeleteMapping
    public ResponseEntity<String> deleteAllTodos() {
        if (todoService.deleteAllTodos()) {
            return ResponseEntity.ok("All Todos Deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete Todos..");
        }
    }

    // - Todo 특정 id로 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return ResponseEntity.noContent().build();
    }
}
