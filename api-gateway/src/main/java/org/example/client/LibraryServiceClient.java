package org.example.client;

import org.example.config.FeignClientConfig;
import org.example.dto.LibraryBookRequestDTO;
import org.example.dto.LibraryBookResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@FeignClient(name = "libraryservice", configuration = FeignClientConfig.class)
public interface LibraryServiceClient {

    @PostMapping("/library/add")
    LibraryBookResponseDTO createBook(@RequestBody LibraryBookRequestDTO libraryBookRequestDTO);

    @DeleteMapping("/library/books/{id}")
    void deleteBook(@PathVariable("id") Long id);

    @GetMapping("/library/books")
    List<LibraryBookResponseDTO> getAllBooks();

    @GetMapping("/library/books/{id}")
    LibraryBookResponseDTO getBook(@PathVariable("id") Long id);

    @GetMapping("/library/books/available")
    ResponseEntity<List<LibraryBookResponseDTO>> getAvailableBooks();

    @PutMapping("/library/books/{id}")
    LibraryBookResponseDTO updateBook(@PathVariable("id") Long id, @RequestBody LibraryBookRequestDTO libraryBookRequestDTO);




}

