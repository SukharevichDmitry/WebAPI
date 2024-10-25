package org.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "library_book", schema = "public")
public class LibraryBook {

    @Id
    @Schema(description = "ID", example = "1")
    private Long id;
    @Schema(description = "Свободна ли книга", example = "true")
    private boolean isAvailable;
    @Column(name = "borrow_time")
    @Schema(description = "Дата, когда книгу взяли", example = "\"2024-10-14 18:45:17.722095\"")
    private LocalDateTime borrowTime;
    @Column(name = "return_time")
    @Schema(description = "Дата, когда книгу необходимо вернуть", example = "\"2024-10-21 18:45:17.722095\"")
    private LocalDateTime returnTime;

    public LibraryBook() {
    }
}
