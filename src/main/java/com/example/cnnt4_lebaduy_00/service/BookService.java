package com.example.cnnt4_lebaduy_00.service;

import com.example.cnnt4_lebaduy_00.modal.Book;
import com.example.cnnt4_lebaduy_00.repository.BookRepo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BookService {
    private final BookRepo bookRepo=new BookRepo();
    public List<Book> getAll() {
        return bookRepo.findAll();
    }

    public List<Book> search(String keyword) {
        return bookRepo.search(keyword);
    }
    public Book findById(long id) {
        return bookRepo.findById(id);
    }

    public void delete(Book book) {
        bookRepo.delete(book);
    }
    public Book addBook(Book bookDto, MultipartFile file) throws IOException {
        String uploadDir = "D:/CNNT4_LeBaDuy_00/CNNT4_LeBaDuy_00/src/main/webapp/images/";


        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = file.getOriginalFilename()
                .replaceAll("\\s+", "_")
                .replaceAll("[()]", "");

        File dest = new File(uploadDir + fileName);
        file.transferTo(dest);


        bookDto.setCoverImage(fileName);
        return bookRepo.save(bookDto);
    }
    public void update(Book book) {
        bookRepo.update(book);
    }
    public void updateImage(Book book, MultipartFile file) throws IOException {
        String uploadDir = "D:/CNNT4_LeBaDuy_00/CNNT4_LeBaDuy_00/src/main/webapp/images/";

        String fileName = file.getOriginalFilename()
                .replaceAll("\\s+", "_")
                .replaceAll("[()]", "");

        File dest = new File(uploadDir + fileName);
        file.transferTo(dest.getAbsoluteFile());

        book.setCoverImage(fileName);
    }

}
