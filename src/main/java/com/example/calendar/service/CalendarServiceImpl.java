package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // 일정 등록
    @Override
    public CalendarResponseDto saveSchedule(CalendarRequestDto dto) {

        Calendar calendar = new Calendar(dto.getTitle(), dto.getTodoist(), dto.getWriter(), dto.getPassword());

        return calendarRepository.saveSchedule(calendar);
    }

    // 일정 목록 조회
    @Override
    public List<CalendarResponseDto> findAllSchedules() {

        return calendarRepository.findAllSchedules();
    }

    // 일정 단건 조회
    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        Calendar calendar = calendarRepository.findScheduleByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }
}
