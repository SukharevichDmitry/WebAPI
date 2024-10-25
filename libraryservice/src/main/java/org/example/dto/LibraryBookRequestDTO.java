package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LibraryBookRequestDTO {
    private Long id;
    private boolean isAvailable;
    private LocalDateTime borrowTime;
    private LocalDateTime returnTime;

    private String title;          // Заголовок книги
    private String author;         // Автор книги
    private String isbn;           // ISBN книги
    private String genre;          // Жанр книги
    private String description;    // Описание книги
}
