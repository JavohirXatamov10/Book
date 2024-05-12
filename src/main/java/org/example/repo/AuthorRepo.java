package org.example.repo;

import org.example.entity.Author;
import org.example.entity.Genre;
import org.springframework.dao.EmptyResultDataAccessException;
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
    public Author findById(Integer authorId) {

        var sql="select *from author where id=?";
        var mapper=BeanPropertyRowMapper.newInstance(Author.class);
        return jdbcTemplate.queryForObject(sql,mapper,authorId);

    }
    public List<Author> findByGenreId(Integer genreId) {
        String sql = "select a.* from author a join public.book b on a.id = b.author_id join genre g on b.genre_id=g.id where g.id=? group by a.id, a.name;";
        var mapper = BeanPropertyRowMapper.newInstance(Author.class);
        return jdbcTemplate.query(sql, mapper,genreId);
    }
    public Author findByIdGenreId(Integer genreId) {
        try {
            String sql = "SELECT a.* FROM author a " +
                    "JOIN book b ON a.id = b.author_id " +
                    "JOIN genre g ON g.id = b.genre_id " +
                    "WHERE g.id = ? " +
                    "LIMIT 1";
            var mapper = BeanPropertyRowMapper.newInstance(Author.class);
            return jdbcTemplate.queryForObject(sql, mapper, genreId);
        } catch (EmptyResultDataAccessException e) {
            // Handle the case when no author is found for the specified genre ID
            return null; // or throw a custom exception, log the error, etc.
        }
    }
}
