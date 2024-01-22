package com.junwoo.todolist.repository;


import com.junwoo.todolist.dto.TodoRequestDto;
import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.entitiy.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo save(Todo todo) {
        return null;
    }

    public TodoResponseDto findById(Long id) {
        return null;
    }

    public List<TodoResponseDto> findAll() {
        return null;
    }

    public Long update(Long id, TodoRequestDto todoRequestDto) {
        return null;
    }

    public Long delete(Long id) {
        return null;
    }
}
