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
package dev.jacobandersen.ddg4j.api;

import java.util.Objects;

/**
 * Represents a dictionary definition of a specific query.
 *
 * @since 1.0.0
 */
public class Definition extends ApiObject {
  private final String text;
  private final String source;
  private final String url;

  /**
   * Creates a new {@link Definition}.
   *
   * @param text   the text of the definition
   * @param source the definition source
   * @param url    the url to more information
   * @since 1.0.0
   */
  public Definition(final String text, final String source, final String url) {
    this.text = text;
    this.source = source;
    this.url = url;
  }

  /**
   * Gets the Definition text.
   *
   * @return the definition text
   * @since 1.0.0
   */
  public String text() {
    return orEmpty(this.text);
  }

  /**
   * Whether or not the definition text has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasText() {
    return has(this.text);
  }

  /**
   * Gets the Definition source.
   *
   * @return the Definition source
   * @since 1.0.0
   */
  public String source() {
    return orEmpty(this.source);
  }

  /**
   * Whether or not the Definition source has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasSource() {
    return has(this.source);
  }

  /**
   * Gets the Definition URL.
   *
   * @return the definition URL
   * @since 1.0.0
   */
  public String url() {
    return orEmpty(this.url);
  }

  /**
   * Whether or not the Definition URL has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasUrl() {
    return has(this.url);
  }

  @Override
  public String instantInformation() {
    final StringBuilder builder = new StringBuilder();

    if(this.hasText()) {
      builder.append(this.text()).append(" (");

      if(this.hasSource() && this.hasUrl()) {
        builder.append(this.hasSource()).append(", ").append(this.hasUrl());
      } else if(this.hasSource()) {
        builder.append(this.hasSource());
      } else if(this.hasUrl()) {
        builder.append(this.hasUrl());
      } else {
        builder.append("unknown source");
      }

      builder.append(")");
    }

    return builder.length() == 0 ? null : builder.toString();
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof Definition)) return false;
    final Definition that = (Definition) o;
    return Objects.equals(this.text(), that.text())
            && Objects.equals(this.source(), that.source())
            && Objects.equals(this.url(), that.url());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.text(), this.source(), this.url());
  }

  @Override
  public String toString() {
    return "Definition{" +
            "text='" + this.text + '\'' +
            ", source='" + this.source + '\'' +
            ", url='" + this.url + '\'' +
            '}';
  }
}
