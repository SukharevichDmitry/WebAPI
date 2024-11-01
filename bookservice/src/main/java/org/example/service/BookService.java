package org.example.service;

import org.example.client.LibraryServiceClient;
import org.example.dto.LibraryBookRequestDTO;
import org.example.dto.BookRequestDTO;
import org.example.dto.BookResponseDTO;
import org.example.entity.Book;
import org.example.exception.BookNotFoundException;
import org.example.exception.InvalidBookException;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.example.mapper.BookMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    @Lazy
    private LibraryServiceClient libraryServiceClient;


    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public BookResponseDTO getBookById(Long id) {
        return bookMapper.toResponseDTO(bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id)));
    }

    public Optional<BookResponseDTO> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn)
                .map(bookMapper::toResponseDTO);
    }


    public BookResponseDTO createBook(BookRequestDTO bookRequestDTO) {
        validateBookRequest(bookRequestDTO);

        Optional<Book> existingBook = bookRepository.findByIsbn(bookRequestDTO.getIsbn());
        if (existingBook.isPresent()) {
            throw new InvalidBookException("Book with that ISBN already exists.");
        }

        Book book = bookMapper.toEntity(bookRequestDTO);
        bookRepository.save(book);

        LibraryBookRequestDTO libraryBookRequestDTO = bookMapper.toLibraryRequestDTO(book);
        libraryServiceClient.sendBook(libraryBookRequestDTO);

        return bookMapper.toResponseDTO(book);
    }


    public BookResponseDTO updateBook(Long id, BookRequestDTO bookDetails) {
        validateBookRequest(bookDetails);

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Optional<Book> existingBook = bookRepository.findByIsbn(bookDetails.getIsbn());
        if (existingBook.isPresent() && !existingBook.get().getId().equals(id)) {
            throw new InvalidBookException("Book with that ISBN already exists.");
        }

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setIsbn(bookDetails.getIsbn());
        book.setGenre(bookDetails.getGenre());
        book.setDescription(bookDetails.getDescription());

        Book updatedBook = bookRepository.save(book);

        LibraryBookRequestDTO libraryBookRequestDTO = bookMapper.toLibraryRequestDTO(updatedBook);
        libraryServiceClient.sendBook(libraryBookRequestDTO);

        return bookMapper.toResponseDTO(book);
    }


    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException(id);
        }

        libraryServiceClient.deleteBook(id);
        bookRepository.deleteById(id);
    }

    private void validateBookRequest(BookRequestDTO bookRequestDTO) {
        if (bookRequestDTO.getTitle() == null || bookRequestDTO.getTitle().isEmpty()) {
            throw new InvalidBookException("Title of book cannot be empty.");
        }
    }

}
