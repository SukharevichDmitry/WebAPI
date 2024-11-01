package org.example.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDTO {

    private String title;

    private String author;

    private String isbn;

    private String genre;

    private String description;

}
