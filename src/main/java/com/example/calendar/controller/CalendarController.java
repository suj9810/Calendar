package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CalendarController {

    private final CalendarService calendarService;

    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    // 일정 생성
    @PostMapping("/schedule")
    public ResponseEntity<CalendarResponseDto> createSchedule(@RequestBody CalendarRequestDto dto) {
        return new ResponseEntity<>(calendarService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 일정 목록 조회
    @GetMapping("/schedule")
    public ResponseEntity<List<CalendarResponseDto>> findAllSchedules() {

        return new ResponseEntity<>(calendarService.findAllSchedules(), HttpStatus.OK);
    }
}
