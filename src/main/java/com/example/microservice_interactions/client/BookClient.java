package com.example.microservice_interactions.client;

import com.example.microservice_interactions.dto.BookDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service", url = "${book-service.url}")
public interface BookClient {

    @GetMapping("/book/{id}") // Deve corresponder ao endpoint no seu BookService
    BookDTO findBookById(@PathVariable("id") Long id);

}
