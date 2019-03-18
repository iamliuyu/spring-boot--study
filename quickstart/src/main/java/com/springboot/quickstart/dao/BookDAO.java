package com.springboot.quickstart.dao;


import com.springboot.quickstart.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1,"Spring Boot实战", 87.7));
        return books;
    }
}
