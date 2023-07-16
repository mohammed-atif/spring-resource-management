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

package com.mohammedatif.rm.mappers;

import com.mohammedatif.rm.models.BaseModel;

import java.util.List;

/**
 * Base mapper interface to define the generic model<->dto mapper</->
 * @param <M> Model
 * @param <D> Dto
 */
public interface BaseMapper <M extends BaseModel, D> {
    M mapToModel(D dto);
    D mapToDto(M model);

    List<M> mapToModelList(List<D> dtoList);
    List<D> mapToDtoList(List<M> modelList);
}
