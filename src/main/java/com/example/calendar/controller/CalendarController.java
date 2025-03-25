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

    // 1️⃣ 일정 생성
    @PostMapping("/schedule")
    public ResponseEntity<CalendarResponseDto> createSchedule(@RequestBody CalendarRequestDto dto) {
        return new ResponseEntity<>(calendarService.saveSchedule(dto), HttpStatus.CREATED);
    }

    // 2️⃣ 일정 목록 조회
    @GetMapping("/schedule")
    public ResponseEntity<List<CalendarResponseDto>> findAllSchedules() {

        return new ResponseEntity<>(calendarService.findAllSchedules(), HttpStatus.OK);
    }

    // 3️⃣ 일정 단건 조회
    @GetMapping("/schedule/{id}")
    public ResponseEntity<CalendarResponseDto> findScheduleById(@PathVariable Long id) {
        return new ResponseEntity<>(calendarService.findScheduleById(id), HttpStatus.OK);
    }

    // 4️⃣ 일정 수정
    @PutMapping("/schedule/{id}")
    public ResponseEntity<CalendarResponseDto> updateSchedules(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto dto
    ) {

        return new ResponseEntity<>(calendarService.updateSchedules(id, dto.getTodoist(), dto.getWriter(), dto.getPassword()), HttpStatus.OK);
    }

    // 5️⃣ 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> deleteSchedules(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto dto
    ) {
        calendarService.deleteSchedules(id, dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
