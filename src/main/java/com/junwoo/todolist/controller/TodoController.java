package com.junwoo.todolist.controller;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.service.TodoService;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/read{id}")
    public TodoResponseDto readTodo(@RequestParam Long id) {
        return todoService.readById(id);
    }
}
