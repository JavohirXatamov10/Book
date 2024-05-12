package org.example.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Genre;
import org.example.repo.AuthorRepo;
import org.example.repo.BookRepo;
import org.example.repo.GenreRepo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("author")
@RequiredArgsConstructor
public class AuthorController {


    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;
    private final AuthorRepo authorRepo;


    @GetMapping("/showAuthorAndBook/{id}")
        public ModelAndView sentToGenreAndBook(@PathVariable("id") Integer genreId, HttpServletRequest request){
            ModelAndView modelAndView = new ModelAndView();
            List<Author> authors=authorRepo.findByGenreId(genreId);
            List<Book> books=bookRepo.findALLByGenre(genreId);
            Genre genre=genreRepo.findById(genreId);
        String authorId1 = request.getParameter("authorId");
        if(authorId1!=null){
                int authorId = Integer.parseInt(authorId1);
                List<Book> books1=  bookRepo.findALLByGenreAndAuthorId(genre.getId(),authorId);
                modelAndView.addObject("books",books1);
            }else {
                modelAndView.addObject("books",books);
            }
            modelAndView.addObject("authors",authors);
            modelAndView.addObject("genre",genre);
            modelAndView.setViewName("authorAndBook"); // Assuming "genreAndBook" is the view name
            return modelAndView;
        }

    }

