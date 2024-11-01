package org.example.mapper;

import org.example.dto.BookResponseDTO;
import org.example.dto.LibraryBookRequestDTO;
import org.example.dto.LibraryBookResponseDTO;
import org.example.entity.LibraryBook;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class LibraryBookMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    // Преобразование из LibraryBookRequestDTO в LibraryBook (для создания/обновления)
    public LibraryBook toEntity(LibraryBookRequestDTO libraryBookRequestDTO) {
        return modelMapper.map(libraryBookRequestDTO, LibraryBook.class);
    }

    // Преобразование из LibraryBook в LibraryBookResponseDTO (для ответа)
    public LibraryBookResponseDTO toResponseDTO(LibraryBook libraryBook) {
        return modelMapper.map(libraryBook, LibraryBookResponseDTO.class);
    }

    // Преобразование списка LibraryBook в список LibraryBookResponseDTO
    public List<LibraryBookResponseDTO> toResponseDTOList(List<LibraryBook> libraryBooks) {
        return libraryBooks.stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Преобразование из LibraryBookResponseDTO в LibraryBook (если потребуется в будущем)
    public LibraryBook toEntity(LibraryBookResponseDTO libraryBookResponseDTO) {
        return modelMapper.map(libraryBookResponseDTO, LibraryBook.class);
    }

}
