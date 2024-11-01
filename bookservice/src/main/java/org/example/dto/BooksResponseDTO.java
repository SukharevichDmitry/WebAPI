package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BooksResponseDTO {
    private List<BookResponseDTO> books;

    public BooksResponseDTO(List<BookResponseDTO> books) {
        this.books = books;
    }

}
