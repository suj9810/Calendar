package com.example.calendar.dto;

import com.example.calendar.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@AllArgsConstructor
public class CalendarResponseDto {

    private Long id;
    private String title;
    private String todoist;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String writer;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.todoist = calendar.getTodoist();
        this.createdAt = calendar.getCreatedAt();
        this.updatedAt = calendar.getUpdatedAt();
        this.writer = calendar.getWriter();
    }
}
