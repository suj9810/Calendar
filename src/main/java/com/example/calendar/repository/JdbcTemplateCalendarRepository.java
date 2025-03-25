package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class JdbcTemplateCalendarRepository implements CalendarRepository {
    
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalendarRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // 1️⃣ 일정 등록
    @Override
    public CalendarResponseDto saveSchedule(Calendar calendar) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id")
                .usingColumns("title", "todoist", "writer", "password");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", calendar.getTitle());
        parameters.put("todoist", calendar.getTodoist());
        parameters.put("writer", calendar.getWriter());
        parameters.put("password", calendar.getPassword());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        List<CalendarResponseDto> result = jdbcTemplate.query("SELECT id, title, todoist, createdAt, updatedAt, writer FROM calendar WHERE id = ?", calendarRowMapper(), key.longValue());

        CalendarResponseDto dto = result.get(0);

        return new CalendarResponseDto(key.longValue(), dto.getTitle(), dto.getTodoist(), dto.getCreatedAt(), dto.getUpdatedAt(), dto.getWriter());
    }

    // 2️⃣ 일정 목록 조회
    @Override
    public List<CalendarResponseDto> findAllSchedules() {
        return jdbcTemplate.query("SELECT id, title, todoist, createdAt, updatedAt, writer FROM calendar", calendarRowMapper());
    }

    // 3️⃣ 일정 단건 조회
    @Override
    public Optional<Calendar> findScheduleById(Long id) {

        List<Calendar> result = jdbcTemplate.query("SELECT * FROM calendar WHERE id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny();
    }

    // 4️⃣ 일정 수정
    @Override
    public int updateSchedules(Long id, String todoist, String writer) {
        return jdbcTemplate.update("UPDATE calendar SET todoist = ?, writer = ?, updatedAt = NOW() WHERE id = ?", todoist, writer, id);
    }

    // 5️⃣ 일정 삭제
    @Override
    public int deleteSchedules(Long id) {
        return jdbcTemplate.update("DELETE FROM calendar WHERE id = ?", id);
    }

    // ✅ id 검증
    @Override
    public Calendar findScheduleByIdOrElseThrow(Long id) {

        List<Calendar> result = jdbcTemplate.query("SELECT * FROM calendar WHERE id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    // ⭕ RowMapperV1 / Password Select ❌
    private RowMapper<CalendarResponseDto> calendarRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("todoist"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getString("writer")
                );
            }
        };
    }

    // ⭕ RowMapperV2 / Password Select ⭕
    private RowMapper<Calendar> calendarRowMapperV2(){
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("todoist"),
                        rs.getTimestamp("createdAt").toLocalDateTime(),
                        rs.getTimestamp("updatedAt").toLocalDateTime(),
                        rs.getString("writer"),
                        rs.getString("password")
                );
            }
        };
    }
}
