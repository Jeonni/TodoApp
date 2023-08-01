package com.coz.TodoApp.controller;

import com.coz.TodoApp.dto.TodoPatchDTO;
import com.coz.TodoApp.dto.TodoPostDTO;
import com.coz.TodoApp.entity.Todo;
import com.coz.TodoApp.entity.TodoMapper;
import com.coz.TodoApp.service.TodoService;
import com.coz.TodoApp.utils.UriCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;
import java.net.URI;
import java.util.List;

@CrossOrigin // https://www.todobackend.com/client/index.html?http://localhost:8080#/
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class TodoController {

    private final TodoService todoService;
    private final TodoMapper mapper;

    // ** Creat
    // - Todo 등록
    @PostMapping
    public ResponseEntity postTodo(@RequestBody TodoPostDTO todoPostDTO) {
        System.out.println("CREATE");
        Todo todo = todoService.addTodo(mapper.todoPostDTOtoTodo(todoPostDTO));
        URI location = UriCreator.createUri("/", todo.getId());
        return ResponseEntity.created(location).body(todo);
    }

    // ** Read
    // - Todo 리스트 조회
    @GetMapping
    public ResponseEntity getAllTodos() {
        System.out.println("READ ALL");
        List<Todo> todos = todoService.getAllTodos();
        return new ResponseEntity(mapper.todoListToTodoResponseDTOList(todos), HttpStatus.OK);
    }

    // - Todo 특정 id로 조회
    @GetMapping("{id}")
    public ResponseEntity getTodoById(@PathVariable("id") @Positive Long id) {
        System.out.println("READ ONE");
        Todo todo = todoService.getTodoById(id);
        return new ResponseEntity(mapper.todoToTodoResponseDTO(todo), HttpStatus.OK);
    }

    // ** Update
    // - Todo 완료 표시  (수정 전)
//    @PutMapping("/{id}/complete")
//    public void completeTodoItem(@PathVariable Long id) {
//        todoService.completeTodoItem(id);
//    }

    // - Todo 수정
    @PatchMapping("{id}")
    public ResponseEntity updateTodo(@PathVariable("id") @Positive Long id, @RequestBody TodoPatchDTO todoPatchDTO) {
        System.out.println("UPDATE");
        todoPatchDTO.setId(id);
        Todo todo = todoService.updateTodo(mapper.todoPatchDTOtoTodo(todoPatchDTO));
        return new ResponseEntity(mapper.todoToTodoResponseDTO(todo), HttpStatus.OK);
    }

    // ** Delete
    // - Todo 삭제
    @DeleteMapping
    public ResponseEntity deleteAllTodos() {
        System.out.println("DELETE ALL");
        todoService.deleteAllTodos();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // - Todo 특정 id로 삭제
    @DeleteMapping("{id}")
    public ResponseEntity deleteTodo(@PathVariable("id") @Positive Long id) {
        System.out.println("DELETE ONE");
        todoService.deleteTodo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
