package org.example.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "book", schema = "public")
@Schema(description = "Объект книги")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID", example = "1")
    private Long id;

    @Schema(description = "Название", example = "Война и мир")
    private String title;

    @Schema(description = "Автор", example = "Лев Николаевич Толстой")
    private String author;

    @Schema(description = "Международный книжный номер", example = "978-5-17-118103-1")
    private String isbn;

    @Schema(description = "Жанр", example = "Роман")
    private String genre;

    @Schema(description = "Описание",
            example = "Ужасно длинная и скучная книга, в которой на протяжении 34 страниц описывается дуб")
    private String description;

    public Book() {
    }
}
