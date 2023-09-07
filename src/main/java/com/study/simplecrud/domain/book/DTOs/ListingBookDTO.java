package com.study.simplecrud.domain.book.DTOs;

import com.study.simplecrud.domain.book.Book;
import com.study.simplecrud.domain.book.Category;
import com.study.simplecrud.domain.book.Model;

public record ListingBookDTO(
        Long isbn,
        String title,
        Integer edition,
        String author,
        Category category,
        Model model
) {
    public ListingBookDTO(Book book) {
        this(book.getIsbn(), book.getTitle(), book.getEdition(), book.getAuthor(), book.getCategory(), book.getModel());
    }
}
