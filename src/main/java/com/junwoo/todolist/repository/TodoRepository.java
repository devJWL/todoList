package com.junwoo.todolist.repository;


import com.junwoo.todolist.dto.TodoResponseDto;
import com.junwoo.todolist.entity.Todo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;


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

    public TodoResponseDto findById(Long id) {
        String sql =  "SELECT * FROM todo where id = ?";
        return jdbcTemplate.query(sql, rs -> {
            TodoResponseDto todoResponseDto;

            if(rs.next()) {
                todoResponseDto = new TodoResponseDto();
                todoResponseDto.setId(rs.getLong("id"));
                todoResponseDto.setTitle(rs.getString("title"));
                todoResponseDto.setContents(rs.getString("contents"));
                todoResponseDto.setWriter(rs.getString("writer"));
                todoResponseDto.setLocalDateTime(rs.getTimestamp("timestamp").toLocalDateTime());
            } else {
                todoResponseDto = null;
            }
            return todoResponseDto;
        }, id);
    }

    public List<TodoResponseDto> findAll() {
        String sql =  "SELECT * FROM todo";
        return jdbcTemplate.query(sql, new RowMapper<TodoResponseDto>() {
            @Override
            public TodoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                // SQL 의 결과로 받아온 Memo 데이터들을 MemoResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String title = rs.getString("title");
                String contents = rs.getString("contents");
                String writer = rs.getString("writer");
                Timestamp timestamp = rs.getTimestamp("timestamp");
                return new TodoResponseDto(id, title, contents, writer, timestamp.toLocalDateTime());
            }
        });
    }
}
