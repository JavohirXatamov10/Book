package org.example.Controller;

import org.example.Config.SpringConfig;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Genre;
import org.example.payload.BookDTO;
import org.example.repo.AuthorRepo;
import org.example.repo.BookRepo;
import org.example.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/library")
public class Library {
    private final AuthorRepo authorRepo;
    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;

    @Autowired
    public Library(AuthorRepo authorRepo, BookRepo bookRepo, GenreRepo genreRepo) {
        this.authorRepo = authorRepo;
        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
    }

    @GetMapping
    public ModelAndView get() {
        ModelAndView modelAndView = new ModelAndView();

        List<Author> authors = authorRepo.findAll();
        List<BookDTO> books = bookRepo.findAll();
        List<Genre> genres = genreRepo.findAll();

        modelAndView.addObject("authors", authors);
        modelAndView.addObject("books", books);
        modelAndView.addObject("genres", genres);

        modelAndView.setViewName("index");
        return modelAndView;
    }
}
