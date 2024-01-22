package com.junwoo.todolist.service;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.entity.Todo;
import com.junwoo.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;


@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponseDto createTodo(TodoRequestDto todoRequestDto) {

        // RequsetDto -> entity
        Todo todo = new Todo(todoRequestDto);

        // DB 저장
        Todo savedTodo = todoRepository.save(todo);

        // entity -> ResponseDto
        TodoResponseDto todoResponseDto = new TodoResponseDto(savedTodo);
        return todoResponseDto;
    }
}
