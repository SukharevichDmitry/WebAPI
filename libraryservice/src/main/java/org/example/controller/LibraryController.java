package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.example.dto.LibraryBookRequestDTO;
import org.example.dto.LibraryBookResponseDTO;
import org.example.dto.LibraryBooksResponseDTO;
import org.example.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/books")
    @Operation(summary = "Получить все книги", description = "Возвращает список всех книг.")
    public ResponseEntity<LibraryBooksResponseDTO> getAllBooks() {
        LibraryBooksResponseDTO response = libraryService.getAllBooks();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/books/{id}")
    @Operation(summary = "Получить книгу по id", description = "Возвращает книгу с указанным id.")
    public LibraryBookResponseDTO getBookById(@PathVariable Long id) {
        return libraryService.getBookById(id);
    }

    @GetMapping("/books/available")
    @Operation(summary = "Получить список свободных книг", description = "Возвращает список свободных книг.")
    public ResponseEntity<List<LibraryBookResponseDTO>> getAvailableBooks() {
        return libraryService.getAllAvailableBooks();
    }

    @PostMapping("/add")
    @Operation(summary = "Создать книгу", description = "Создаёт книгу с указанными параметрами.")
    @ResponseStatus(HttpStatus.CREATED)
    public LibraryBookResponseDTO createBook(@RequestBody LibraryBookRequestDTO libraryBookRequestDTO) {
        return libraryService.createBook(libraryBookRequestDTO);
    }

    @PutMapping("/books/{id}")
    @Operation(summary = "Изменить книгу", description = "Изменяет параметры книги с указанным id на указанные")
    public LibraryBookResponseDTO updateBook(@PathVariable Long id, @RequestBody LibraryBookRequestDTO libraryBookDTO) {
        return libraryService.updateBook(id, libraryBookDTO);
    }

    @DeleteMapping("/books/{id}")
    @Operation(summary = "Удалить книгу", description = "Удаляет книгу с указанным id.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
    }
}
