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

import java.util.Arrays;
import java.util.Locale;
import java.util.Optional;

/**
 * Provides a utility for finding enum constants by name.
 *
 * @since 1.0.0
 */
public final class EnumUtil {
  private EnumUtil() {
  }

  /**
   * Gets an Enum constant by name if it exists.
   *
   * @param enumClass the Enum class to search
   * @param name      the name to search for
   * @param <T>       the Enum type
   * @return the Enum constant wrapped in an optional, or an empty optional if the constant could not be found
   * @since 1.0.0
   */
  public static <T extends Enum<T>> Optional<T> constantByName(final Class<T> enumClass, final String name) {
    if(name == null || name.isEmpty()) {
      return Optional.empty();
    }

    return Arrays.stream(enumClass.getEnumConstants())
            .filter(type -> type.name().equals(
                    name.replaceAll("_", "").toUpperCase(Locale.ROOT).trim()
            ))
            .findFirst();
  }

  /**
   * Gets an Enum constant by name if it exists, or returns a default value instead.
   *
   * @param enumClass the Enum class to search
   * @param name      the name to search for
   * @param def       the default fallback value
   * @param <T>       the Enum type
   * @return the Enum constant if it exists or the default value instead
   * @since 1.0.0
   */
  public static <T extends Enum<T>> T constantByNameOrElse(final Class<T> enumClass, final String name, final T def) {
    return constantByName(enumClass, name).orElse(def);
  }
}
