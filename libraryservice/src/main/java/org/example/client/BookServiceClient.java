package org.example.client;

import org.example.dto.BookResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookservice", url = "${feign.client.book.url}")
public interface BookServiceClient {

    @GetMapping("/books/{id}")
    BookResponseDTO getBookById(@PathVariable("id") Long id);
}

