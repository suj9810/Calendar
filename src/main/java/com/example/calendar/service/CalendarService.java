package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;

import java.util.Date;
import java.util.List;

public interface CalendarService {

    // 1️⃣ 일정 생성
    CalendarResponseDto saveSchedule(CalendarRequestDto dto);

    // 2️⃣ 일정 목록 조회
    List<CalendarResponseDto> findAllSchedules();

    // 3️⃣ 일정 단건 조회
    CalendarResponseDto findScheduleById(Long id);

    // 4️⃣ 일정 수정
    CalendarResponseDto updateSchedules(Long id, String todoist, String writer, String password);

    // 5️⃣ 일정 삭제
    void deleteSchedules(Long id, String password);
}
