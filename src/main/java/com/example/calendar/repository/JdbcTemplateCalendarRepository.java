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
    
    // 일정 등록
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

        List<CalendarResponseDto> result = jdbcTemplate.query("select id, title, todoist, createdAt, updatedAt, writer from calendar where id = ?", calendarRowMapper(), key.longValue());

        CalendarResponseDto dto = result.get(0);

        return new CalendarResponseDto(key.longValue(), dto.getTitle(), dto.getTodoist(), dto.getCreatedAt(), dto.getUpdatedAt(), dto.getWriter());
    }

    // 일정 목록 조회
    @Override
    public List<CalendarResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from calendar", calendarRowMapper());
    }

    // 일정 단건 조회
    @Override
    public Optional<Calendar> findScheduleById(Long id) {

        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny();
    }

    // id 검증
    @Override
    public Calendar findScheduleByIdOrElseThrow(Long id) {

        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }


    private RowMapper<CalendarResponseDto> calendarRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("todoist"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt"),
                        rs.getString("writer")
                );
            }
        };
    }

    private RowMapper<Calendar> calendarRowMapperV2(){
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("todoist"),
                        rs.getDate("createdAt"),
                        rs.getDate("updatedAt"),
                        rs.getString("writer"),
                        rs.getString("password")
                );
            }
        };
    }
}
