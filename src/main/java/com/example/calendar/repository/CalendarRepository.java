package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

import java.util.List;

public interface CalendarRepository {

    // 일정 등록
    CalendarResponseDto saveSchedule(Calendar calendar);

    // 일정 목록 조회
    List<CalendarResponseDto> findAllSchedules();
}
