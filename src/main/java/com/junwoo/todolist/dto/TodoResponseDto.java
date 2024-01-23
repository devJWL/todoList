package com.junwoo.todolist.dto;

import com.junwoo.todolist.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class TodoResponseDto {
    //아이디, 할일 제목,할일 내용, 담당자, 작성일
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime localDateTime;
    private HttpStatus httpStatus;
    public TodoResponseDto() {

    }

    public TodoResponseDto(Todo todo, HttpStatus httpStatus) {
        this.id = todo.getId();
        this.title = todo.getTitle();
        this.contents = todo.getContents();
        this.writer = todo.getWriter();
        this.localDateTime = todo.getLocalDateTime();
        this.httpStatus = httpStatus;
    }
}
