package com.junwoo.todolist.dto;

import com.junwoo.todolist.entity.Todo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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

    public TodoResponseDto() {

    }

    public TodoResponseDto(Todo saveTodo) {
        this.id = saveTodo.getId();
        this.title = saveTodo.getTitle();
        this.contents = saveTodo.getContents();
        this.writer = saveTodo.getWriter();
        this.localDateTime = saveTodo.getLocalDateTime();
    }

}
