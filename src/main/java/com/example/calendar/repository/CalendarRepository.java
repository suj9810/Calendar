package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

import java.util.List;
import java.util.Optional;

public interface CalendarRepository {

    // 일정 등록
    CalendarResponseDto saveSchedule(Calendar calendar);

    // 일정 목록 조회
    List<CalendarResponseDto> findAllSchedules();

    // 일정 단건 조회
    Optional<Calendar> findScheduleById(Long id);

    // id 검증
    Calendar findScheduleByIdOrElseThrow(Long id);

    // 일정 수정
    int updateSchedules(Long id, String todoist, String writer);

    // 일정 삭제
    int deleteSchedules(Long id);
}
