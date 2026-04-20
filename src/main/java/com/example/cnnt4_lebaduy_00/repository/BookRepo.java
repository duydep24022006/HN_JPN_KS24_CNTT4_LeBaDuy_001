package com.example.cnnt4_lebaduy_00.repository;

import com.example.cnnt4_lebaduy_00.modal.Book;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class BookRepo {
    private final Map<Long, Book> storage = new HashMap<>();

    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }

    public Book findById(Long id) {
        return storage.get(id);
    }

    public Book save(Book book) {
        storage.put(book.getId(), book);
        return book;
    }

    public void delete(Book book) {
        storage.remove(book.getId());
    }

    public void update(Book book) {
        storage.put(book.getId(), book);
    }

    public List<Book> search(String keyword) {
        keyword = keyword.toLowerCase();
        List<Book> books = new ArrayList<>();
        for (Book b : storage.values()) {
            if (b.getTitle().toLowerCase().contains(keyword) || b.getAuthor().toLowerCase().contains(keyword)) {
                books.add(b);
            }
        }
        return books;
    }
}
