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

import com.mohammedatif.rm.dtos.BookStoreDto;
import com.mohammedatif.rm.params.BookStoreParam;
import com.mohammedatif.rm.services.BookStoreService;
import com.mohammedatif.rm.utils.Constants;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("bookstores")
public class BookStoreController {

    private final BookStoreService bookStoreService;

    public BookStoreController(final BookStoreService bookStoreService) {
        this.bookStoreService = bookStoreService;
    }
    
    @GetMapping("/{id}")
    public BookStoreDto getBookStoreById(@PathVariable final int id) {
        return bookStoreService.getBookStoreById(id).orElseThrow();
    }

    @PostMapping
    public BookStoreDto createBookStore(@RequestBody final BookStoreDto request) {
        return bookStoreService.createBookStore(request);
    }

    @PostMapping("/{id}")
    public BookStoreDto updateBookStore(
            @PathVariable final int id,
            @RequestBody final BookStoreDto request
    ) {
        return bookStoreService.updateBookStore(id, request);
    }

    @GetMapping
    public List<BookStoreDto> getAllBookStoresBy(
            @RequestParam(name = Constants.RequestParams.AUTHOR_ID, required = false) final Integer authorId,
            @RequestParam(name = Constants.RequestParams.BOOK_ID, required = false) final Integer bookId
    ) {
        return bookStoreService.getAllBookStores(new BookStoreParam(authorId, bookId));
    }

}
