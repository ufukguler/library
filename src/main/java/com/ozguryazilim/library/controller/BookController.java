package com.ozguryazilim.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ozguryazilim.library.repository.BookRepo;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookRepo bookRepo;

}
