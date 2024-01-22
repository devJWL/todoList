package com.junwoo.todolist.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Getter
@Setter
public class TodoResponseDto {
    //아이디, 할일 제목,할일 내용, 담당자, 작성일
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime localDateTime;
}
