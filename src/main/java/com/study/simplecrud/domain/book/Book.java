package com.study.simplecrud.domain.book;

import com.study.simplecrud.domain.book.DTOs.RegisterBookDTO;
import com.study.simplecrud.domain.book.DTOs.UpdateBookDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "books")
@Entity(name = "Book")
@Getter
@ToString
@NoArgsConstructor
public class Book {
    @Id
    private Long isbn;
    private String title;
    private Integer edition;
    private String author;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Model model;
    private Boolean active;

    public Book(RegisterBookDTO data) {
        this.active = true;
        this.isbn = data.isbn();
        this.title = data.title();
        this.edition = data.edition();
        this.author = data.author();
        this.category = data.category();
        this.model = data.model();
    }

    public void updateBookInfo(UpdateBookDTO data) {
        if (data.author() != null) {
            this.author = data.author();
        }

        if (data.category() != null) {
            this.category = data.category();
        }

        if (data.edition() != null) {
            this.edition = data.edition();
        }

        if (data.title() != null) {
            this.title = data.title();
        }

        if (data.model() != null) {
            this.model = data.model();
        }
    }

    public void delete() {
        this.active = false;
    }

    public Long getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public Integer getEdition() {
        return edition;
    }

    public String getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public Model getModel() {
        return model;
    }
}
