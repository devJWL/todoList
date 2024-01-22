package com.junwoo.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter

public class TodoRequestDto {
    //할일 제목,할일 내용, 담당자, 비밀번호, 작성일
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDateTime localDateTime;

}
