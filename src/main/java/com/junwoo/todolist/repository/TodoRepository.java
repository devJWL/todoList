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
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


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
        TodoResponseDto todoResponseDto = null;
        try {
            Todo todo = findByIdHelper(id).orElseThrow(() -> new Exception("해당 번호의 글은 존재하지 않습니다."));
            todoResponseDto = new TodoResponseDto(todo);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return todoResponseDto;
    }

    public List<TodoResponseDto> findAll() {
        String sql =  "SELECT * FROM todo";
        List<TodoResponseDto> todoResponseDtoList = jdbcTemplate.query(sql, new RowMapper<TodoResponseDto>() {
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
        todoResponseDtoList.sort(new Comparator<TodoResponseDto>() {
            @Override
            public int compare(TodoResponseDto o1, TodoResponseDto o2) {
                return o1.getLocalDateTime().compareTo(o2.getLocalDateTime());
            }
        });
        return todoResponseDtoList;
    }

    public TodoResponseDto delete(Long id, String password) {
        Optional<TodoResponseDto> todoResponseDto = Optional.empty();
        try {
            Todo todo = findByIdHelper(id).orElseThrow(() -> new Exception("해당 번호의 글은 존재하지 않습니다."));
            if (todo.getPassword().equals(password)) {
                String sql = "DELETE FROM todo WHERE id = ?";
                jdbcTemplate.update(sql, id);
                todoResponseDto = Optional.of(new TodoResponseDto(todo));
            }
            else {
                throw new Exception("비밀번호가 다릅니다");
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return todoResponseDto.orElse(new TodoResponseDto());
    }


    private Optional<Todo> findByIdHelper(Long id) {
        String sql =  "SELECT * FROM todo where id = ?";
        return jdbcTemplate.query(sql, rs -> {
            Todo todo = null;
            if(rs.next()) {
                todo = new Todo();
                todo.setId(rs.getLong("id"));
                todo.setTitle(rs.getString("title"));
                todo.setContents(rs.getString("contents"));
                todo.setWriter(rs.getString("writer"));
                todo.setPassword(rs.getString("password"));
                todo.setLocalDateTime(rs.getTimestamp("timestamp").toLocalDateTime());
            }
            return Optional.ofNullable(todo);
        }, id);
    }
}
