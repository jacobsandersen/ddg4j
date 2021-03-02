/*
 * This file is part of ddg4j, licensed under the MIT License.
 *
 * Copyright (c) 2021 Jacob Andersen (simpleauthority)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package dev.jacobandersen.ddg4j.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dev.jacobandersen.ddg4j.api.SearchResult;

/**
 * Provides an {@link kong.unirest.ObjectMapper} that has a registered Deserializer for {@link SearchResult}.
 * This object proxies {@link ObjectMapper}.
 *
 * @since 1.0.0
 */
public class CustomObjectMapper implements kong.unirest.ObjectMapper {
  private final ObjectMapper mapper;

  /**
   * Constructs a new {@link CustomObjectMapper}.
   *
   * @since 1.0.0
   */
  public CustomObjectMapper() {
    final ObjectMapper mapper = new ObjectMapper();
    final SimpleModule deserializerModule = new SimpleModule();
    deserializerModule.addDeserializer(SearchResult.class, new SearchResultDeserializer());
    mapper.registerModule(deserializerModule);

    this.mapper = mapper;
  }

  @Override
  public <T> T readValue(final String value, final Class<T> valueType) {
    try {
      return this.mapper.readValue(value, valueType);
    } catch(final JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }

  @Override
  public String writeValue(final Object value) {
    try {
      return this.mapper.writeValueAsString(value);
    } catch(final JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }
  }
}
