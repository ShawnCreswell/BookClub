package com.shawn.bookclub.services;

import com.shawn.bookclub.models.Book;
import com.shawn.bookclub.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repo;
    public BookService(BookRepository repo) {
        this.repo = repo;
    }
    // CREATE
    public Book create(Book book) {
        return repo.save(book);
    }
    // READ
    public List<Book> getAll() {
        return repo.findAll();
    }
    public Book getOne(Long id) {
        Optional<Book> book = repo.findById(id);
        return book.orElse(null);
    }
    // UPDATE
    public Book update(Book book) {
        return repo.save(book);
    }
    //DELETE
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
