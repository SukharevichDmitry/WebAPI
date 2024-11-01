package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookResponseDTO {

    private Long id;

    private String title;

    private String author;

    private String isbn;

    private String genre;

    private String description;

}
