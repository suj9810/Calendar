package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CalendarServiceImpl implements CalendarService {

    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    // 1️⃣ 일정 등록
    @Override
    public CalendarResponseDto saveSchedule(CalendarRequestDto dto) {

        Calendar calendar = new Calendar(dto.getTitle(), dto.getTodoist(), dto.getWriter(), dto.getPassword());

        return calendarRepository.saveSchedule(calendar);
    }

    // 2️⃣ 일정 목록 조회
    @Override
    public List<CalendarResponseDto> findAllSchedules(String writer, LocalDate updatedAt) {

        return calendarRepository.findAllSchedules(writer, updatedAt);
    }

    // 3️⃣ 일정 단건 조회
    @Override
    public CalendarResponseDto findScheduleById(Long id) {

        Calendar calendar = calendarRepository.findScheduleByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }

    // 4️⃣ 일정 수정
    @Transactional
    @Override
    public CalendarResponseDto updateSchedules(Long id, String todoist, String writer, String password) {

        Calendar calendar = calendarRepository.findScheduleByIdOrElseThrow(id);

        // ✅ 비밀번호 검증
        if (!calendar.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        if (todoist == null || writer == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The title and writer are required values.");
        }

        int updateRow = calendarRepository.updateSchedules(id, todoist, writer);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        Calendar updateCalendar = calendarRepository.findScheduleByIdOrElseThrow(id);

        return new CalendarResponseDto(updateCalendar);
    }

    // 5️⃣ 일정 삭제
    @Override
    public void deleteSchedules(Long id, String password) {

        Calendar calendar = calendarRepository.findScheduleByIdOrElseThrow(id);

        // ✅ 비밀번호 검증
        if (!calendar.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid password");
        }

        int updateRow = calendarRepository.deleteSchedules(id);

        if (updateRow == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

    }
}
