package org.example.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookRequestDTO {

    private String title;

    private String author;

    private String isbn;

    private String genre;

    private String description;

}
