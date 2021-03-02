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
package dev.jacobandersen.ddg4j;

import dev.jacobandersen.ddg4j.api.SearchResult;
import dev.jacobandersen.ddg4j.util.CustomObjectMapper;
import kong.unirest.Unirest;

/**
 * The entrypoint for searching DuckDuckGo.
 *
 * @since 1.0.0
 */
public final class DuckDuckGo {
  private static final String BASE_URL = "https://api.duckduckgo.com/?no_redirect=1&format=json&q=";
  private static boolean initialized = false;

  private DuckDuckGo() {
  }

  /**
   * Search DuckDuckGo and get the SearchResult object to work with.
   *
   * @param query the query to search for
   * @return the SearchResult
   * @since 1.0.0
   */
  public static SearchResult search(final String query) {
    if(!initialized) {
      Unirest.config().setObjectMapper(new CustomObjectMapper());
      initialized = true;
    }

    return Unirest.get(String.format("%s%s", BASE_URL, query))
            .asObject(SearchResult.class)
            .getBody();
  }
}
