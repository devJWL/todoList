package com.junwoo.todolist.entitiy;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
public class Todo {
    // 아이디, 할일 제목,할일 내용, 담당자, 비밀번호, 작성일
    private Long id;
    private String title;
    private String contents;
    private String writer;
    private String password;
    private LocalDateTime localDateTime;

}
