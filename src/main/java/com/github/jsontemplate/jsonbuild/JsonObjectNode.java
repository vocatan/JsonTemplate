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

package com.github.jsontemplate.jsonbuild;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class JsonObjectNode implements JsonNode {

    private Map<String, JsonNode> children = new LinkedHashMap<>();

    public static JsonObjectNode of(Map<String, ?> valueMap) {
        JsonObjectNode jsonObjectNode = new JsonObjectNode();
        valueMap.forEach((key, value) -> jsonObjectNode.putNode(key, JsonNode.of(value)));
        return jsonObjectNode;
    }

    public void putInteger(String key, Supplier<Integer> supplier) {
        children.put(key, new JsonIntegerNode(supplier));
    }

    public void putFloat(String key, Supplier<Float> supplier) {
        children.put(key, new JsonFloatNode(supplier));
    }

    public void putBoolean(String key, Supplier<Boolean> supplier) {
        children.put(key, new JsonBooleanNode(supplier));
    }

    public void putString(String key, Supplier<String> supplier) {
        children.put(key, new JsonStringNode(supplier));
    }

    public void putObject(String key, JsonObjectNode value) {
        children.put(key, value);
    }

    public void putArray(String key, JsonArrayNode value) {
        children.put(key, value);
    }

    public void putNull(String key) {
        children.put(key, new JsonNullNode());
    }

    public void putWrapper(String key, JsonWrapperNode wrapperNode) {
        children.put(key, wrapperNode);
    }

    @Override
    public String print() {
        String joinedChildren = children.entrySet().stream()
                .map(entry -> entry.getKey() + ":" + entry.getValue().print())
                .collect(Collectors.joining(","));
        return "{" + joinedChildren + "}";
    }

    @Override
    public String prettyPrint(int identation) {
        String childSpaces = JsonNodeUtils.makeIdentation(identation + 1);
        String joinedIdentChildren = children.entrySet().stream()
                .map(entry -> childSpaces + "\"" + entry.getKey() + "\" : " + entry.getValue().prettyPrint(identation + 1))
                .collect(Collectors.joining(",\n"));
        String spaces = JsonNodeUtils.makeIdentation(identation);
        return "{\n" +
                joinedIdentChildren +
                "\n" + spaces + "}";
    }

    public void putNode(String key, JsonNode node) {
        this.children.put(key, node);
    }
}
