package org.example.service;

import org.example.client.BookServiceClient;
import org.example.dto.BookResponseDTO;
import org.example.dto.LibraryBookRequestDTO;
import org.example.dto.LibraryBookResponseDTO;
import org.example.entity.LibraryBook;
import org.example.exception.LibraryBookNotFoundException;
import org.example.mapper.LibraryBookMapper;
import org.example.repository.LibraryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Autowired
    private LibraryBookMapper libraryBookMapper;

    @Autowired
    @Lazy
    private BookServiceClient bookServiceClient;

    public List<LibraryBookResponseDTO> getAllBooks() {
        List<LibraryBook> books = libraryBookRepository.findAll();
        return libraryBookMapper.toResponseDTOList(books);
    }

    public LibraryBookResponseDTO getBookById(Long id) {
        LibraryBook book = libraryBookRepository.findById(id)
                .orElseThrow(() -> new LibraryBookNotFoundException(id));
        return libraryBookMapper.toResponseDTO(book);
    }

    public ResponseEntity<List<LibraryBookResponseDTO>> getAllAvailableBooks() {
        List<LibraryBook> availableBooks = libraryBookRepository.findAll().stream()
                .filter(LibraryBook::isAvailable)
                .collect(Collectors.toList());

        if (availableBooks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        List<LibraryBookResponseDTO> resultBooks = availableBooks.stream()
                .map(this::getLibraryBookResponseDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(resultBooks);
    }

    private LibraryBookResponseDTO getLibraryBookResponseDTO(LibraryBook libraryBook) {
        BookResponseDTO bookResponseDTO = bookServiceClient.getBookById(libraryBook.getId());

        LibraryBookResponseDTO libraryBookResponseDTO = libraryBookMapper.toResponseDTO(libraryBook);
        if (bookResponseDTO != null) {
            libraryBookResponseDTO.setTitle(bookResponseDTO.getTitle());
            libraryBookResponseDTO.setAuthor(bookResponseDTO.getAuthor());
            libraryBookResponseDTO.setDescription(bookResponseDTO.getDescription());
            libraryBookResponseDTO.setGenre(bookResponseDTO.getGenre());
            libraryBookResponseDTO.setIsbn(bookResponseDTO.getIsbn());
        }
        return libraryBookResponseDTO;
    }

    public LibraryBookResponseDTO createBook(LibraryBookRequestDTO libraryBookRequestDTO) {  // Изменение возвращаемого типа
        LibraryBook libraryBook = libraryBookMapper.toEntity(libraryBookRequestDTO);
        return libraryBookMapper.toResponseDTO(libraryBookRepository.save(libraryBook));
    }

    public LibraryBookResponseDTO updateBook(Long id, LibraryBookRequestDTO libraryBookRequestDTO) {
        LibraryBook libraryBook = libraryBookRepository.findById(id)
                .orElseThrow(() -> new LibraryBookNotFoundException(id));

        libraryBook.setAvailable(libraryBookRequestDTO.isAvailable());
        libraryBook.setBorrowTime(libraryBookRequestDTO.getBorrowTime());
        libraryBook.setReturnTime(libraryBookRequestDTO.getReturnTime());

        return libraryBookMapper.toResponseDTO(libraryBookRepository.save(libraryBook));
    }

    public void deleteBook(Long id) {
        libraryBookRepository.deleteById(id);
    }
}
