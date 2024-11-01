package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class LibraryBooksResponseDTO {
    private List<LibraryBookResponseDTO> books;

    public LibraryBooksResponseDTO(List<LibraryBookResponseDTO> books) {
        this.books = books;
    }

}
