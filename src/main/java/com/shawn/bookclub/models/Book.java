package com.shawn.bookclub.models;

import javax.persistence.*;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String thoughts;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    public Book() {}
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}
    public String getAuthor() {return author;}
    public void setAuthor(String author) {this.author = author;}
    public String getThoughts() {return thoughts;}
    public void setThoughts(String thoughts) {this.thoughts = thoughts;}
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}
}