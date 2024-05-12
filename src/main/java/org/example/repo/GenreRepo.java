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
        var sql = "select *from genre";
        var mapper = BeanPropertyRowMapper.newInstance(Genre.class);
        return jdbcTemplate.query(sql, mapper);
    }
    public List<Genre> findByAuthorId(Integer authorId) {
        String sql = "SELECT g.* FROM genre g  JOIN book b ON g.id = b.genre_id JOIN author a ON a.id = b.author_id WHERE a.id = ? group by g.id";
        var mapper = BeanPropertyRowMapper.newInstance(Genre.class);
        return jdbcTemplate.query(sql, mapper, authorId);
    }
    public Genre findById(Integer genreId) {
        var sql = "select *from genre where id=?";
        var mapper = BeanPropertyRowMapper.newInstance(Genre.class);
        return jdbcTemplate.queryForObject(sql, mapper,genreId);
    }
}
