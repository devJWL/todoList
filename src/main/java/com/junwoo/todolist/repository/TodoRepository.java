package com.junwoo.todolist.repository;


import com.junwoo.todolist.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;


@Repository
public class TodoRepository {
    private final JdbcTemplate jdbcTemplate;
    public TodoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Todo save(Todo todo) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체
        todo.setLocalDateTime(LocalDateTime.now());
        String sql = "INSERT INTO todo (title, contents, writer, password, timestamp) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update( con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, todo.getTitle());
                    preparedStatement.setString(2, todo.getContents());
                    preparedStatement.setString(3, todo.getWriter());
                    preparedStatement.setString(4, todo.getPassword());
                    preparedStatement.setTimestamp(5, Timestamp.valueOf(todo.getLocalDateTime()));
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        todo.setId(id);
        return todo;
    }
}
