package org.example.repo;

import org.example.entity.Book;
import org.example.entity.Genre;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GenreRepo {
    private final JdbcTemplate jdbcTemplate;

    public GenreRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Genre> findAll() {
        var sql="select *from genre";
        var mapper= BeanPropertyRowMapper.newInstance(Genre.class);
        return jdbcTemplate.query(sql, mapper);

    }
}
