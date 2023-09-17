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

import com.mohammedatif.rm.dtos.AuthorDto;
import com.mohammedatif.rm.mappers.AuthorMapper;
import com.mohammedatif.rm.params.AuthorParam;
import com.mohammedatif.rm.repositories.AddressRepository;
import com.mohammedatif.rm.repositories.AuthorRepository;
import com.mohammedatif.rm.services.AuthorService;
import com.mohammedatif.rm.springevents.configs.ResourceLifecycleEvent;
import com.mohammedatif.rm.springevents.publishers.EventPublisher;
import com.mohammedatif.rm.springevents.resources.AuthorResourceEvent;
import org.mapstruct.factory.Mappers;
import org.springframework.context.ApplicationEvent;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private static final AuthorMapper authorMapper = Mappers.getMapper(AuthorMapper.class);

    private final AuthorRepository authorRepository;
    private final AddressRepository addressRepository;

    private final EventPublisher<ApplicationEvent> localEventPublisher;

    public AuthorServiceImpl(
            final AuthorRepository authorRepository,
            final AddressRepository addressRepository,
            final EventPublisher<ApplicationEvent> localEventPublisher
    ) {
        this.authorRepository = authorRepository;
        this.addressRepository = addressRepository;
        this.localEventPublisher = localEventPublisher;
    }

    @Override
    public Optional<AuthorDto> getAuthorById(final int id) {
        return authorRepository.findById(id).map(authorMapper::mapToDto);
    }

    @Override
    public List<AuthorDto> getAllAuthors(@NonNull final AuthorParam param) {
        return authorMapper.mapToDtoList(authorRepository.findAllBy());
    }

    @Override
    @Transactional
    public AuthorDto createAuthor(@NonNull final AuthorDto request) {
        final var requestModel = authorMapper.mapToModel(request);
        final var updatedAddress = addressRepository.save(requestModel.getOfficeAddress());
        requestModel.setOfficeAddress(updatedAddress);

        final var updatedModel = authorRepository.save(requestModel);
        localEventPublisher.publishEvent(new AuthorResourceEvent(
                this,
                updatedModel.getId(),
                ResourceLifecycleEvent.OperationType.CREATED
        ));
        return authorMapper.mapToDto(updatedModel);
    }

    @Override
    public AuthorDto updateAuthor(final int id, @NonNull final AuthorDto request) {
        localEventPublisher.publishEvent(new AuthorResourceEvent(
                this,
                id,
                ResourceLifecycleEvent.OperationType.UPDATED
        ));
        return null;
    }
}
