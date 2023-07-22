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

import com.mohammedatif.rm.dtos.AuthorDto;
import com.mohammedatif.rm.params.AuthorParam;
import com.mohammedatif.rm.services.AuthorService;
import com.mohammedatif.rm.utils.Constants;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequestMapping("authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(final AuthorService authorService) {
        this.authorService = authorService;
    }
    
    @GetMapping("/{id}")
    public AuthorDto getAuthorById(@PathVariable final int id) {
        return authorService.getAuthorById(id).orElseThrow();
    }

    @PostMapping
    public AuthorDto createAuthor(@RequestBody @Valid final AuthorDto request) {
        return authorService.createAuthor(request);
    }

    @PostMapping("/{id}")
    public AuthorDto updateAuthor(
            @PathVariable final int id,
            @RequestBody final AuthorDto request
    ) {
        return authorService.updateAuthor(id, request);
    }

    @GetMapping
    public List<AuthorDto> getAllAuthorsBy(
            @RequestParam(name = Constants.RequestParams.BOOK_ID, required = false) final Integer bookId,
            @RequestParam(name = Constants.RequestParams.BOOKSTORE_ID, required = false) final Integer bookstoreId
    ) {
        return authorService.getAllAuthors(new AuthorParam(bookId, bookstoreId));
    }

}
