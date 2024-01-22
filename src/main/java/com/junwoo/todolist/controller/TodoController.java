package com.junwoo.todolist.controller;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/junwoo")
public class TodoController {
    private final TodoService todoService;
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @PostMapping("/create")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return todoService.createTodo(todoRequestDto);
    }

    @GetMapping("/read")
    public TodoResponseDto readTodo(@RequestParam Long id) {
        return todoService.readById(id);
    }

    @GetMapping("/read/list")
    public List<TodoResponseDto> readAllTodo() {
        return todoService.readAll();
    }

//    @PutMapping("/update/{id}")
//    public TodoResponseDto updateTodo(@RequestParam Long id, @RequestBody TodoRequestDto todoRequestDto) {
//        return todoService.updateTodo(id, todoRequestDto);
//    }
    @DeleteMapping("/delete")
    public TodoResponseDto deleteTodo(@RequestParam Long id, @RequestParam String password) {
        return todoService.deleteTodo(id, password);
    }
}
