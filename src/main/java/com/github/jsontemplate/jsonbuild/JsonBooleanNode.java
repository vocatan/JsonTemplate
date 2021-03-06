/*
 * Copyright 2019 the original author or authors.
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
 * limitations under the License.
 */

package com.github.jsontemplate.jsonbuild;

import java.util.function.Supplier;

/**
 * This class represents a producer of a json boolean value.
 */
public final class JsonBooleanNode extends AbstractJsonValueNode<Boolean> {

    public JsonBooleanNode(Supplier<Boolean> supplier) {
        super(supplier);
    }

    /**
     * Creates a JsonBooleanNode with a given boolean value.
     *
     * @param value the boolean object to be converted
     * @return the converted json boolean node
     */
    public static JsonBooleanNode of(Boolean value) {
        return new JsonBooleanNode(() -> value);
    }

    @Override
    public String compactString() {
        return Boolean.toString(supplier.get());
    }

}
