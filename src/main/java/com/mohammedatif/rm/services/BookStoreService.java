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

package com.mohammedatif.rm.services;

import com.mohammedatif.rm.dtos.BookStoreDto;
import com.mohammedatif.rm.params.BookStoreParam;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface BookStoreService {
    Optional<BookStoreDto> getBookStoreById(int id);
    List<BookStoreDto> getAllBookStores(@NonNull BookStoreParam param);

    BookStoreDto createBookStore(@NonNull BookStoreDto request);
    BookStoreDto updateBookStore(int id, @NonNull BookStoreDto request);
}
