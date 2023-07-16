/*
 *    Copyright 2023 Mohammed Atif
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.mohammedatif.rm.controllers;

import com.mohammedatif.rm.dtos.BookDto;
import com.mohammedatif.rm.params.BookParam;
import com.mohammedatif.rm.services.BookService;
import com.mohammedatif.rm.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable final int id) {
        return bookService.getBookById(id).orElseThrow();
    }

    @PostMapping
    public BookDto createBook(@RequestBody final BookDto request) {
        return bookService.createBook(request);
    }

    @PostMapping("/{id}")
    public BookDto updateBook(
            @PathVariable final int id,
            @RequestBody final BookDto request
    ) {
        return bookService.updateBook(id, request);
    }

    @GetMapping
    public List<BookDto> getAllBooksBy(
            @RequestParam(name = Constants.RequestParams.AUTHOR_ID, required = false) final Integer authorId,
            @RequestParam(name = Constants.RequestParams.BOOKSTORE_ID, required = false) final Integer bookstoreId
    ) {
        return bookService.getAllBooks(new BookParam(authorId, bookstoreId));
    }

}
