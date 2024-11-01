package org.example.controller;

import org.example.client.AuthClient;
import org.example.dto.*;
import org.example.client.BookServiceClient;
import org.example.client.LibraryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;

@RestController
public class GatewayController {
    private static final Logger log = LoggerFactory.getLogger(GatewayController.class);


    @Autowired
    private BookServiceClient bookServiceClient;

    @Autowired
    private LibraryServiceClient libraryServiceClient;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private TokenHolder tokenHolder;

    @GetMapping("/books")
    public ResponseEntity<BooksResponseDTO> getAllBooks() {
        return bookServiceClient.getAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        return bookServiceClient.getBookById(id);
    }

    @GetMapping("/books/isbn/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        return bookServiceClient.getBookByIsbn(isbn);
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        return bookServiceClient.createBook(bookRequestDTO);
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookRequestDTO) {
        return bookServiceClient.updateBook(id, bookRequestDTO);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookServiceClient.deleteBook(id);
    }

    @GetMapping("/library/books")
    public LibraryBooksResponseDTO getAllLibraryBooks() {
        return libraryServiceClient.getAllBooks();
    }

    @PostMapping("/library/add")
    public ResponseEntity<LibraryBookResponseDTO> addLibraryBook(@RequestBody LibraryBookRequestDTO libraryBookRequestDTO) {
        return libraryServiceClient.createBook(libraryBookRequestDTO);
    }

    @DeleteMapping("/library/books/{id}")
    public void deleteLibraryBook(@PathVariable Long id) {
        libraryServiceClient.deleteBook(id);
    }

    @GetMapping("/library/books/{id}")
    public LibraryBookResponseDTO getLibraryBookById(@PathVariable Long id) {
        return libraryServiceClient.getBook(id);
    }

    @GetMapping("/library/books/available")
    public ResponseEntity<List<LibraryBookResponseDTO>> getAvailableBooks() {
        return libraryServiceClient.getAvailableBooks();
    }

    @PutMapping("/library/books/{id}")
    public LibraryBookResponseDTO updateLibraryBook(@PathVariable Long id, @RequestBody LibraryBookRequestDTO libraryBookRequestDTO) {
        return libraryServiceClient.updateBook(id, libraryBookRequestDTO);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO){
        return authClient.register(userDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO) {
        ResponseEntity<AuthResponseDTO> response = authClient.login(userDTO);
        if (response.getStatusCode().is2xxSuccessful()) {
            String token = Objects.requireNonNull(response.getBody()).getToken();
            tokenHolder.setToken(token);
        }
        return response;
    }
}
