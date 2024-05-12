package org.example.Controller;

import org.example.payload.BookDTO;
import org.example.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookRepo bookRepo;
    @Autowired
    public BookController(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }
    @GetMapping("/showBooks/{id}")
    public ModelAndView showAllBooks(@PathVariable(name = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        BookDTO bookDTO = bookRepo.findById(id);
        modelAndView.addObject("book", bookDTO);
        modelAndView.setViewName("allBooks");
        return modelAndView;
    }
    @GetMapping("/showGenreBook/{id}")
    public ModelAndView showAllBooksInGenrePage(@PathVariable(name = "id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        BookDTO bookDTO = bookRepo.findById(id);
        modelAndView.addObject("book", bookDTO);
        modelAndView.setViewName("allBooks");
        return modelAndView;
    }
    @GetMapping("/back")
    public String backTo(){
        return "redirect:/library";
    }



}
