/*
 * Copyright 2019 Haihan Yin
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

package com.github.jsontemplate.valueproducer;

import com.github.jsontemplate.jsonbuild.JsonStringNode;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ipv6NodeProducer extends AbstractNodeProducer<JsonStringNode> {

    private static final String LETTERS = "0123456789abcdef";

    private Random random = new Random();

    @Override
    public JsonStringNode produce() {
        return new JsonStringNode(this::produceIp);
    }

    protected String produceIp() {
        return IntStream.range(0, 8)
                .mapToObj(i -> produceGroup())
                .collect(Collectors.joining(":"));
    }

    private String produceGroup() {
        int length = LETTERS.length();
        char[] group = new char[]{
                LETTERS.charAt(random.nextInt(length)),
                LETTERS.charAt(random.nextInt(length)),
                LETTERS.charAt(random.nextInt(length)),
                LETTERS.charAt(random.nextInt(length))
        };
        return new String(group);
    }
}
