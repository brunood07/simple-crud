package com.study.simplecrud.domain.book.DTOs;

import com.study.simplecrud.domain.book.Category;
import com.study.simplecrud.domain.book.Model;
import jakarta.validation.constraints.*;

public record RegisterBookDTO(
        @NotBlank
        Long isbn,

        @NotBlank
        String title,

        @NotNull
        Integer edition,

        @NotBlank
        String author,

        @NotNull
        Category category,

        @NotNull
        Model model
) {

}
