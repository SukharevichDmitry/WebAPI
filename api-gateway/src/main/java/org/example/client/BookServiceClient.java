package org.example.client;

import org.example.config.FeignClientConfig;
import org.example.dto.BookResponseDTO;
import org.example.dto.BookRequestDTO;
import org.example.dto.BooksResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bookservice", configuration = FeignClientConfig.class)
public interface BookServiceClient {
    @GetMapping("/books/{id}")
    ResponseEntity<BookResponseDTO> getBookById(@PathVariable("id") Long id);

    @GetMapping("/books")
    ResponseEntity<BooksResponseDTO> getAllBooks();

    @PostMapping("/books")
    ResponseEntity<BookResponseDTO> createBook(BookRequestDTO bookRequestDTO);

    @GetMapping("/books/isbn/{isbn}")
    ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable("isbn") String isbn);

    @PutMapping("/books/{id}")
    ResponseEntity<BookResponseDTO> updateBook(@PathVariable("id") Long id, BookRequestDTO bookRequestDTO);

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable("id") Long id);
}
