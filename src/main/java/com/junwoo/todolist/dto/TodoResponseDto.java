package com.junwoo.todolist.dto;

import com.junwoo.todolist.entitiy.Todo;
import lombok.Getter;

import java.time.LocalDateTime;



@Getter
public class TodoResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime localDateTime;


    public TodoResponseDto(Todo todo) {
    }
}
