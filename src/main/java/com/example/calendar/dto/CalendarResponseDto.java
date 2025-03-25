package com.example.calendar.dto;

import com.example.calendar.entity.Calendar;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
