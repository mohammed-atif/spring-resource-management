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

package com.mohammedatif.rm.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDto> handleValidationExceptions(final MethodArgumentNotValidException ex) {
        final var responseFormat = "'%s' %s";
        final var errorResponse = new ExceptionDto(400);
        ex.getBindingResult().getAllErrors().forEach(error -> {
            final var message =  String.format(responseFormat,
                    ((FieldError) error).getField(),
                    error.getDefaultMessage() != null ? error.getDefaultMessage() : ""
            ).trim();
            errorResponse.addError(message);
        });

        return new ResponseEntity<>(
                errorResponse,
                HttpStatusCode.valueOf(errorResponse.getStatus())
        );
    }
}
