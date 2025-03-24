package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;

import java.util.List;

public interface CalendarService {
    
    // 일정 등록
    CalendarResponseDto saveSchedule(CalendarRequestDto dto);

    // 일정 목록 조회
    List<CalendarResponseDto> findAllSchedules();

    // 일정 단건 조회
    CalendarResponseDto findScheduleById(Long id);

    // 일정 수정
    CalendarResponseDto updateSchedules(Long id, String todoist, String writer, String password);

    // 일정 삭제
    void deleteSchedules(Long id, String password);
}
