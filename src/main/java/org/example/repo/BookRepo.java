package org.example.repo;

import org.example.entity.Book;
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

    public List<BookDTO> findAllDTO() {
            var sql="select b.id,b.name,a.name as authorName,g.name as genre from book b join public.author a on a.id = b.author_id JOIN genre g on g.id = b.genre_id";
            var mapper= BeanPropertyRowMapper.newInstance(BookDTO.class);
            return jdbcTemplate.query(sql, mapper);

    }

    public List<Book> findALL() {
        var sql="select * from book";
        var mapper=BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(sql,mapper);
    }

    public BookDTO findById(Integer id) {
        var sql="select b.id,b.name,a.name as authorName,g.name as genre from book b join public.author a on a.id = b.author_id JOIN genre g on g.id = b.genre_id where b.id=?";
            var mapper=BeanPropertyRowMapper.newInstance(BookDTO.class);
        return jdbcTemplate.queryForObject(sql, mapper, id);
    }


    public List<Book>
    findALLByGenreAndAuthorId(int genreId, Integer authorId) {
        var sql="select *from  book where genre_id=? and author_id=?";
        var mapper=BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(sql, mapper,genreId,authorId);

    }

    public List<Book> findALLByAuthorId(Integer authorId) {
        var sql="select *from  book where author_id=?";
        var mapper=BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(sql, mapper,authorId);
    }

    public List<Book> findALLByGenre(Integer genreId) {
        var sql="select *from  book where genre_id=?";
        var mapper=BeanPropertyRowMapper.newInstance(Book.class);
        return jdbcTemplate.query(sql, mapper,genreId);
    }
}
