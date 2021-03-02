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

import dev.jacobandersen.ddg4j.util.EnumUtil;

/**
 * Represents the overall type of search result.
 *
 * @since 1.0.0
 */
public enum Type {
  A("Article"),
  D("Disambiguation"),
  C("Category"),
  N("Name"),
  E("Exclusive"),
  NULL(null);

  private final String meaning;

  /**
   * Creates a {@link Type} with the given meaning.
   *
   * @param meaning the meaning of the {@link Type}.
   */
  Type(final String meaning) {
    this.meaning = meaning;
  }

  /**
   * Gets the meaning for the {@link Type}.
   *
   * @return the meaning
   * @since 1.0.0
   */
  public String meaning() {
    return this.meaning;
  }

  /**
   * Whether or not this Type is an instance of {@link #NULL}.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean isNull() {
    return this == NULL;
  }

  /**
   * Gets a {@link Type} by name, or returns {@link #NULL} if such a constant does not exist.
   *
   * @param name the name of the Type
   * @return the Type, or {@link #NULL}
   * @since 1.0.0
   */
  public static Type byName(final String name) {
    return EnumUtil.constantByNameOrElse(Type.class, name, NULL);
  }
}
