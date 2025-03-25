package com.example.calendar.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class Calendar {

    private Long id;
    private String title;
    private String todoist;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String writer;
    private String password;

    public Calendar(String title, String todoist, String writer, String password) {
        this.title = title;
        this.todoist = todoist;
        this.writer = writer;
        this.password = password;
    }
}
