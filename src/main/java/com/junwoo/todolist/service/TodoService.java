package com.junwoo.todolist.service;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.entitiy.Todo;
import com.junwoo.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {
        Todo todo = new Todo(todoRequestDto);

        Todo saveTodo = todoRepository.save(todo);

        TodoResponseDto todoResponseDto = new TodoResponseDto(todo);
        return todoResponseDto;
    }

    public Optional<TodoResponseDto> getTodoById(Long id) {
        Optional<TodoResponseDto> todoResponseDto = Optional.ofNullable(todoRepository.findById(id));
        return todoResponseDto;
    }

    public List<TodoResponseDto> getTodoList() {
        return todoRepository.findAll();
    }

    public Long updateTodo(Long id, TodoRequestDto todoRequestDto) {
        TodoResponseDto todo = todoRepository.findById(id);
        return todoRepository.update(id, todoRequestDto);
    }

    public Long deleteTodo(Long id) {
        return todoRepository.delete(id);
    }
}
