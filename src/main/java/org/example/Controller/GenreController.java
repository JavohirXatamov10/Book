package org.example.Controller;
import jakarta.servlet.http.HttpServletRequest;
import org.example.entity.Author;
import org.example.entity.Book;
import org.example.entity.Genre;
import org.example.repo.AuthorRepo;
import org.example.repo.BookRepo;
import org.example.repo.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("genre")
public class GenreController {
    private final BookRepo bookRepo;
    private final GenreRepo genreRepo;
    private final AuthorRepo authorRepo;
    @Autowired
    public GenreController(BookRepo bookRepo, GenreRepo genreRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.genreRepo = genreRepo;
        this.authorRepo = authorRepo;
    }
    @GetMapping("/showGenreAndBook/{id}")
    public ModelAndView sentToGenreAndBook(@PathVariable("id") Integer authorId, HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();


        List<Genre>genres=genreRepo.findByAuthorId(authorId);
        List<Book> books=bookRepo.findALLByAuthorId(authorId);
        Author author= authorRepo.findById(authorId);



        String genreid = request.getParameter("genreid");
        if(genreid!=null){
            int genreId = Integer.parseInt(genreid);
           List<Book> books1=  bookRepo.findALLByGenreAndAuthorId(genreId,authorId);
            modelAndView.addObject("books",books1);
        }else {
            modelAndView.addObject("books",books);
        }
        modelAndView.addObject("genres",genres);
        modelAndView.addObject("chosenAuthor",author);
        modelAndView.setViewName("genreAndBook"); // Assuming "genreAndBook" is the view name
        return modelAndView;
    }

}
