package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.BookRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.service.BookService;
import org.example.dto.BookResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    @Operation(summary = "Получить все книги", description = "Возвращает список всех книг.")
    public List<BookResponseDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить книгу по id", description = "Возвращает книгу с указанным id.")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO bookResponseDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @GetMapping("/isbn/{isbn}")
    @Operation(summary = "Получить книгу по isbn", description = "Возвращает книгу с указанным isbn.")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable String isbn) {
        return bookService.getBookByIsbn(isbn)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Создать книгу", description = "Создаёт книгу с указанными параметрами.")
    @ResponseStatus(HttpStatus.CREATED) // Статус 201 для создания
    public ResponseEntity<BookResponseDTO> createBook(@RequestBody BookRequestDTO bookRequestDTO) {
        BookResponseDTO bookResponseDTO = bookService.createBook(bookRequestDTO);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Изменить книгу", description = "Изменяет параметры книги с указанным id на указанные")
    public  ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookRequestDTO bookDetails) {
        BookResponseDTO bookResponseDTO = bookService.updateBook(id, bookDetails);
        return ResponseEntity.ok(bookResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить книгу", description = "Удаляет книгу с указанным id.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}

