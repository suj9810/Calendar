package com.example.calendar.dto;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
public class CalendarRequestDto {

    private String title;
    private String todoist;
    private String writer;
    private LocalDateTime updatedAt;
    private String password;
}