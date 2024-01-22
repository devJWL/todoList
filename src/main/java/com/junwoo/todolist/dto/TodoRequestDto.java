package com.junwoo.todolist.dto;

import lombok.Getter;

import java.time.LocalDateTime;


@Getter
public class TodoRequestDto {

    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDateTime localDateTime;
}
