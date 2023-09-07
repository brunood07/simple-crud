package com.study.simplecrud.controller;

import com.study.simplecrud.domain.book.*;
import com.study.simplecrud.domain.book.DTOs.ListingBookDTO;
import com.study.simplecrud.domain.book.DTOs.RegisterBookDTO;
import com.study.simplecrud.domain.book.DTOs.UpdateBookDTO;
import com.study.simplecrud.domain.book.DTOs.UpdateBookDTOResponse;
import com.study.simplecrud.domain.book.repository.BooksRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BooksRepository booksRepository;

    @GetMapping
    public ResponseEntity<Page<ListingBookDTO>> list(@PageableDefault(size = 10, sort = {"title"}) Pageable pagination) {
        var books = booksRepository.findAllByActiveTrue(pagination);
        return ResponseEntity.ok(books.map(ListingBookDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ListingBookDTO> find(@PathVariable Long id) {
        var book = booksRepository.getReferenceById(id);
        return ResponseEntity.ok().body(new ListingBookDTO(book));
    }

    @ResponseBody
    @Transactional
    @PostMapping
    public ResponseEntity save(@RequestBody RegisterBookDTO data, UriComponentsBuilder uriBuilder) {
        var book = new Book(data);
        booksRepository.save(book);

        var uri = uriBuilder.path("/books/{id}").buildAndExpand(book.getIsbn()).toUri();

        return ResponseEntity.created(uri).body(new UpdateBookDTOResponse(book));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity update(@RequestBody UpdateBookDTO data, @PathVariable Long id) {
        var book = booksRepository.getReferenceById(id);
        book.updateBookInfo(data);
        return ResponseEntity.ok(new UpdateBookDTOResponse(book));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        var book = booksRepository.getReferenceById(id);
        book.delete();
        return ResponseEntity.noContent().build();
    }
}
