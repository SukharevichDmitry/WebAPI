package org.example.exception;

public class LibraryBookNotFoundException extends RuntimeException {
    public LibraryBookNotFoundException(Long id) {
        super("Library book not found: " + id);
    }
}
