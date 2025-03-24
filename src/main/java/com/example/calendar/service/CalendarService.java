package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;

public interface CalendarService {
    
    // 일정 등록
    CalendarResponseDto saveSchedule(CalendarRequestDto dto);
}
