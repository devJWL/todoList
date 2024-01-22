package com.junwoo.todolist.service;


import com.junwoo.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;


@Service
public class TodoService {
    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

}
