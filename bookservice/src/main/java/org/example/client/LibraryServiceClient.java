package org.example.client;

import org.example.dto.LibraryBookRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "libraryservice", url = "${feign.client.library.url}")
public interface LibraryServiceClient {

    @PostMapping("/library/add")
    void sendBook(@RequestBody LibraryBookRequestDTO libraryBookRequestDTO);

    @DeleteMapping("/library/books/{id}")
    void deleteBook(@PathVariable("id") Long id);
}
