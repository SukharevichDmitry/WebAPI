package org.example.controller;

import org.example.client.AuthClient;
import org.example.dto.*;
import org.example.client.BookServiceClient;
import org.example.client.LibraryServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GatewayController {

    @Autowired
    private BookServiceClient bookServiceClient;

    @Autowired
    private LibraryServiceClient libraryServiceClient;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private TokenHolder tokenHolder;

    @GetMapping("/books")
    public List<BookResponseDTO> getAllBooks() {
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
    public List<LibraryBookResponseDTO> getAllLibraryBooks() {
        return libraryServiceClient.getAllBooks();
    }

    @PostMapping("/library/add")
    public void addLibraryBook(@RequestBody LibraryBookRequestDTO libraryBookRequestDTO) {
        libraryServiceClient.createBook(libraryBookRequestDTO);
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
    public void updateLibraryBook(@PathVariable Long id, @RequestBody LibraryBookRequestDTO libraryBookRequestDTO) {
        libraryServiceClient.updateBook(id, libraryBookRequestDTO);
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@RequestBody UserDTO userDTO){
        return authClient.register(userDTO);
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserDTO userDTO) {
        ResponseEntity<AuthResponseDTO> response = authClient.login(userDTO);
        if (response.getStatusCode().is2xxSuccessful()) {
            String token = response.getBody().getToken();
            tokenHolder.setToken(token);
        }
        return response;
    }
}
