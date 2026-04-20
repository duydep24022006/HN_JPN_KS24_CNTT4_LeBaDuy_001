package com.example.cnnt4_lebaduy_00.controller;

import com.example.cnnt4_lebaduy_00.dto.BookDto;
import com.example.cnnt4_lebaduy_00.modal.Book;
import com.example.cnnt4_lebaduy_00.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class BookController {
    private final BookService bookService=new BookService();

    @GetMapping("/")
    public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
        if (keyword == null) {
            model.addAttribute("book", bookService.getAll());
        } else {
            model.addAttribute("book", bookService.search(keyword));
        }
        return "book";
    }

    @GetMapping("/new")
    public String newBook(Model model) {
        model.addAttribute("bookDto",new BookDto());
        return "book-form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute BookDto bookDto,
                           BindingResult bindingResult,
                           Model model) throws Exception  {
        if (bookDto.getCoverImage() == null || bookDto.getCoverImage().isEmpty()) {
            bindingResult.rejectValue("productImage", "error.productImage", "Ảnh sản phẩm không được để trống");
        }

        if (bindingResult.hasErrors()) {
            return "book-form";
        }

        Book book = new Book();
        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());

        bookService.addBook(book, bookDto.getCoverImage());

        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        Book book=bookService.findById(id);
        bookService.delete(book);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showFormEdit(@PathVariable("id") long id, Model model) {
        Book book=bookService.findById(id);
        if (book == null) {
            return "redirect:/";
        }
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setQuantity(book.getQuantity());
        model.addAttribute("bookDto", bookDto);
        model.addAttribute("bookId", id);
        model.addAttribute("currentImage", book.getCoverImage());
        return "book-edit";
    }
    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id,
                         @Valid @ModelAttribute BookDto bookDto,
                         BindingResult bindingResult,
                         Model model
    )throws Exception {
        if (bindingResult.hasErrors()) {
            Book existing = bookService.findById(id);
            model.addAttribute("bookId", id);
            model.addAttribute("currentImage", existing.getCoverImage());
            return "book-edit";
        }
        Book book=bookService.findById(id);
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setQuantity(bookDto.getQuantity());

        boolean hasNewImage = bookDto.getCoverImage() != null
                && !bookDto.getCoverImage().isEmpty();
        if (hasNewImage) {
            bookService.updateImage(book, bookDto.getCoverImage());
        }

        bookService.update(book);
        return "redirect:/";
    }

}
