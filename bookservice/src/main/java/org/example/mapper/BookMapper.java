package org.example.mapper;

import org.example.dto.BookRequestDTO;
import org.example.dto.BookResponseDTO;
import org.example.dto.LibraryBookRequestDTO;
import org.example.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    // Преобразование из BookRequestDTO в Book (для создания/обновления)
    public Book toEntity(BookRequestDTO bookRequestDTO) {
        return modelMapper.map(bookRequestDTO, Book.class);
    }

    // Преобразование из Book в BookResponseDTO (для ответа)
    public BookResponseDTO toResponseDTO(Book book) {
        return modelMapper.map(book, BookResponseDTO.class);
    }

    // Преобразование из Book в LibraryBookRequestDTO (для взаимодействия с LibraryService)
    public LibraryBookRequestDTO toLibraryRequestDTO(Book book) {
        LibraryBookRequestDTO libraryBookRequestDTO = new LibraryBookRequestDTO();
        libraryBookRequestDTO.setId(book.getId());
        libraryBookRequestDTO.setAvailable(true);  // По умолчанию, книга доступна
        libraryBookRequestDTO.setBorrowTime(null);
        libraryBookRequestDTO.setReturnTime(null);
        return libraryBookRequestDTO;
    }
}
