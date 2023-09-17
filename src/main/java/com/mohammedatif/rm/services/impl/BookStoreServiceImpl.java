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

import com.mohammedatif.rm.dtos.BookStoreDto;
import com.mohammedatif.rm.mappers.BookStoreMapper;
import com.mohammedatif.rm.params.BookStoreParam;
import com.mohammedatif.rm.repositories.BookStoreRepository;
import com.mohammedatif.rm.services.BookStoreService;
import com.mohammedatif.rm.springevents.resources.AuthorResourceEvent;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BookStoreServiceImpl implements BookStoreService {

    private static final BookStoreMapper bookstoreMapper = Mappers.getMapper(BookStoreMapper.class);

    private final BookStoreRepository bookStoreRepository;

    public BookStoreServiceImpl(final BookStoreRepository bookStoreRepository) {
        this.bookStoreRepository = bookStoreRepository;
    }

    @Override
    public Optional<BookStoreDto> getBookStoreById(final int id) {
        return bookStoreRepository.findById(id).map(bookstoreMapper::mapToDto);
    }

    @Override
    public List<BookStoreDto> getAllBookStores(@NonNull final BookStoreParam param) {
        return bookstoreMapper.mapToDtoList(bookStoreRepository.findAllBy());
    }

    @Override
    public BookStoreDto createBookStore(@NonNull final BookStoreDto request) {
        final var requestModel = bookstoreMapper.mapToModel(request);
        final var updatedModel = bookStoreRepository.save(requestModel);
        return bookstoreMapper.mapToDto(updatedModel);
    }

    @Override
    public BookStoreDto updateBookStore(final int id, @NonNull final BookStoreDto request) {
        return null;
    }

    @EventListener(condition = "#event.operationType.name() == 'CREATED'")
    public void onAuthorAdded(final AuthorResourceEvent event) {
        log.info("Author Added {}", event.getResourceId());
        throw new RuntimeException();
    }

    @EventListener(condition = "#event.operationType.name() == 'UPDATED'")
    public void onAuthorUpdated(final AuthorResourceEvent event) {
        log.info("Author Added {}", event.getResourceId());
        throw new RuntimeException();
    }

    @EventListener(condition = "#event.operationType.name() == 'REMOVED'")
    public void onAuthorRemoved(final AuthorResourceEvent event) {
        log.info("Author Added {}", event.getResourceId());
        throw new RuntimeException();
    }
}
