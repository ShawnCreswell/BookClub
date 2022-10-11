package com.shawn.bookclub.controllers;

import com.shawn.bookclub.models.Book;
import com.shawn.bookclub.services.BookService;
import com.shawn.bookclub.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class BookController {
    @Autowired
    BookService service;
    @Autowired
    UserService userService;

    @GetMapping("/books/new")
    String createForm(@ModelAttribute("book") Book book, Model model, HttpSession session) {
        model.addAttribute("user", userService.getOne((Long) session.getAttribute("userId")));
        return "createBook.jsp";
    }
    @PostMapping("/books")
    String create(@ModelAttribute("book") Book book) {
        service.create(book);
        return "redirect:/books";
    }
    @GetMapping("/books")
    String showAll(Model model, HttpSession session) {
        List<Book> books = service.getAll();
        model.addAttribute("user", userService.getOne((Long) session.getAttribute("userId")));
        model.addAttribute("books", books);
        return "books.jsp";
    }
    @GetMapping("/books/{id}")
    String showOne(@PathVariable Long id, Model model, HttpSession session) {
        Book book = service.getOne(id);
        model.addAttribute("book", book);
        model.addAttribute("user", userService.getOne((Long) session.getAttribute("userId")));
        return "showBook.jsp";
    }
    @GetMapping("/books/{id}/edit")
    String updateForm(@PathVariable("id") Long id, Model model, HttpSession session) {
        Book book = service.getOne(id);
        if (!Objects.equals((Long)session.getAttribute("userId"), book.getUser().getId())) {
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        return "updateBook.jsp";
    }
    @PutMapping("/books/{id}")
    String update(
            @Valid
            @ModelAttribute("book") Book book,
            BindingResult result) {
        if (result.hasErrors()) {
            return "updateBook.jsp";
        } else {
            service.update(book);
            return "redirect:/books";
        }
    }
    @DeleteMapping("/books/{id}")
    String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/books";
    }
}