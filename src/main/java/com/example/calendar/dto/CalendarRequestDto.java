package com.example.calendar.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CalendarRequestDto {

    private String title;
    private String todoist;
    private String writer;
    private Date updatedAt;
    private String password;
}