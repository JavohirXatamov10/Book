package org.example.repo;

import org.example.entity.Author;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class AuthorRepo {
    private final JdbcTemplate jdbcTemplate;
    public AuthorRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Author> findAll() {
        var sql="select * from author";
        var mapper= BeanPropertyRowMapper.newInstance(Author.class);
        return jdbcTemplate.query(sql, mapper);
    }
}
