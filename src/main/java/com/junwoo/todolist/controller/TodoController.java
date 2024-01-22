package com.junwoo.todolist.controller;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/junwoo")

public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping("/todo/create")
    public TodoResponseDto createTodo(@RequestBody TodoRequestDto todoRequestDto) {
        return null;
    }

    @GetMapping("/todo/{id}")
    public Optional<TodoResponseDto> getTodoById(@PathVariable Long id) {
        return null;
    }


    @GetMapping("/todo/allList")
    public List<TodoResponseDto> getTodoList() {
        return null;
    }

    @PutMapping("/todoList/update/{id}")
    public Long updateTodo(@PathVariable Long id, @PathVariable String password, @RequestBody TodoRequestDto todoRequestDto) {
        return 1L;
    }

    @DeleteMapping("/todoList/delete/{id}")
    public Long deleteTodo(@PathVariable Long id, @PathVariable String password) {
        return null;
    }
}
