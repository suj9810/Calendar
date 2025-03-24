package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import org.springframework.stereotype.Service;

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
}
