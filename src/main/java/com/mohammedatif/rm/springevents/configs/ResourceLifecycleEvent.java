/*
 * Copyright 2023 Mohammed Atif
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.mohammedatif.rm.springevents.configs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@EqualsAndHashCode(callSuper = true)
public abstract class ResourceLifecycleEvent<T> extends ApplicationEvent {

    private final Class<T> resourceClass;

    @Getter
    private final String resourceId;


    protected ResourceLifecycleEvent(
            final Object source,
            final Class<T> resourceClass,
            final String resourceId
    ) {
        super(source);
        this.resourceClass = resourceClass;
        this.resourceId = resourceId;
    }
}