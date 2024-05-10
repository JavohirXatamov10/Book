package org.example.repo;

import org.example.payload.BookDTO;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepo {
    private final JdbcTemplate jdbcTemplate;
    public BookRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<BookDTO> findAll() {
            var sql="select b.id,b.name,a.name as authorName,g.name as genre from book b join public.author a on a.id = b.author_id JOIN genre g on g.id = b.genre_id";
            var mapper= BeanPropertyRowMapper.newInstance(BookDTO.class);
            return jdbcTemplate.query(sql, mapper);

    }
}
