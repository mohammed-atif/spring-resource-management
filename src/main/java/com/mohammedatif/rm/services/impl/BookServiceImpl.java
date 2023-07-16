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

package com.mohammedatif.rm.services.impl;

import com.mohammedatif.rm.dtos.BookDto;
import com.mohammedatif.rm.mappers.BookMapper;
import com.mohammedatif.rm.params.BookParam;
import com.mohammedatif.rm.repositories.BookRepository;
import com.mohammedatif.rm.services.BookService;
import org.mapstruct.factory.Mappers;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    private final BookRepository bookRepository;

    public BookServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Optional<BookDto> getBookById(final int id) {
        return bookRepository.findById(id).map(bookMapper::mapToDto);
    }

    @Override
    public List<BookDto> getAllBooks(@NonNull final BookParam param) {
        return bookMapper.mapToDtoList(bookRepository.findAllBy());
    }

    @Override
    public BookDto createBook(@NonNull final BookDto request) {
        final var requestModel = bookMapper.mapToModel(request);
        final var updatedModel = bookRepository.save(requestModel);
        return bookMapper.mapToDto(updatedModel);
    }

    @Override
    public BookDto updateBook(final int id, @NonNull final BookDto request) {
        return null;
    }
}
