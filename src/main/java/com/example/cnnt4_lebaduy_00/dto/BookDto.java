package com.example.cnnt4_lebaduy_00.dto;

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

public class BookDto {
    private long id;

    @NotBlank(message = "Không đc bỏ trống Title")
    @Size(min = 3, max = 100, message = "Tên sản phẩm phải từ 3 đến 100 ký tự")
    private String title;

    @NotBlank(message = "Không đc bỏ trống author")
    @Pattern(regexp = "\\S+", message = "Thương hiệu không được chứa khoảng trắng")
    private String author;

    @NotNull(message = "Số lượng không được để trống")
    @Min(value = 0, message = "Số lượng phải >= 0")
    @Digits(integer = 10, fraction = 0, message = "Số lượng phải là số nguyên")
    private Integer quantity;

    private MultipartFile coverImage;

    public BookDto(long id, String title, String author, Integer quantity, MultipartFile coverImage) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.coverImage = coverImage;
    }

    public BookDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(MultipartFile coverImage) {
        this.coverImage = coverImage;
    }
}
