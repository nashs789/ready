package com.example.ready.controller;

import com.example.ready.Service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {

    public final BooksService booksService;

    @PostMapping
    public void insertBooks() {
        booksService.insertBooks();
    }
}