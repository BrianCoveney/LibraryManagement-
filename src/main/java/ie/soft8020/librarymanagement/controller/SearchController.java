package ie.soft8020.librarymanagement.controller;

import ie.soft8020.librarymanagement.domain.Book;
import ie.soft8020.librarymanagement.forms.SearchForm;
import ie.soft8020.librarymanagement.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;


@Controller
public class SearchController implements WebMvcConfigurer {

    @Autowired
    IBookService bookService;;

    private Book book;


    @RequestMapping(value = "/searchtitle")
    public String searchBooksByTitle(SearchForm searchForm) { return "searchtitle"; }

    @RequestMapping(value = "/searchtitle", method = RequestMethod.POST)
    public String searchBooksByTitle(@ModelAttribute @Valid SearchForm searchForm,
                                     BindingResult bindingResult, Model model) throws EmptyResultDataAccessException {

        if (bindingResult.hasErrors()) {
            System.out.println("Binding results error!");
            return "searchtitle";
        } else {
            String sanitizedTitleStr = sanitizeForSearch(searchForm.getTitle());
            book = bookService.getByTitle(sanitizedTitleStr);
        }

        model.addAttribute("searchForm", book);
        model.addAttribute("booksAll", bookService.findAll());

        return "searchtitle";
    }

    private String sanitizeForSearch(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The argument cannot be null");
        }
        return str
                // Remove punctuation
                .replace("`", " ").replace("!", " ").replace("#", " ").replace("$", " ").replace("^", " ")
                .replace("&", " ").replace("[", " ").replace("]", " ").replace("{", " ").replace("}", " ").replace("|", " ")
                .replace(";", " ").replace("*", " ").replace(".", " ").replace("?", " ").replace("'", " ").replace("/", " ")
                // Prevent injection
                .replace("=", " ")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                // Remove leading/trailing whitespace
                .trim();
    }
}
