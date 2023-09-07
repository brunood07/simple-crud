package com.study.simplecrud.domain.book.repository;

import com.study.simplecrud.domain.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {
    Page<Book> findAllByActiveTrue(Pageable pagination);
}
