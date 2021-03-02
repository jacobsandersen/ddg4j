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

import java.util.Objects;

/**
 * Represents a direct answer to a question.
 *
 * @since 1.0.0
 */
public class Answer extends ApiObject {
  private final String answer;
  private final Type type;

  /**
   * Constructs a new Answer object.
   *
   * @param answer the answer text (may contain HTML)
   * @param type   the answer type
   * @since 1.0.0
   */
  public Answer(final String answer, final Type type) {
    this.answer = answer;
    this.type = type;
  }

  /**
   * Get the answer text (may contain HTML).
   *
   * @return the answer text
   * @since 1.0.0
   */
  public String answer() {
    return orEmpty(this.answer);
  }

  /**
   * Whether or not the answer text has any content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasAnswer() {
    return has(this.answer);
  }

  /**
   * Get the type of answer (see {@link Answer.Type}.
   *
   * @return the answer type
   * @since 1.0.0
   */
  public Type type() {
    return this.type;
  }

  @Override
  public String instantInformation() {
    final StringBuilder builder = new StringBuilder();

    if(this.hasAnswer()) {
      builder.append("[").append(this.type()).append("] ").append(this.answer());
    }

    return builder.length() == 0 ? null : builder.toString();
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof Answer)) return false;
    final Answer answer1 = (Answer) o;
    return Objects.equals(this.answer(), answer1.answer())
            && this.type() == answer1.type();
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.answer(), this.type());
  }

  @Override
  public String toString() {
    return "Answer{" +
            "answer='" + this.answer + '\'' +
            ", type=" + this.type +
            '}';
  }

  /**
   * Represents the type of answer. The fallback is {@link #ANSWER}.
   *
   * @since 1.0.0
   */
  public enum Type {
    CALC,
    COLOR,
    DIGEST,
    INFO,
    IP,
    IPLOC,
    PHONE,
    PW,
    RAND,
    REGEXP,
    UNICODE,
    UPC,
    ZIP,
    // Unknown answer type
    ANSWER;

    /**
     * Gets an Answer Type by its name.
     *
     * @param name the raw name
     * @return the Type or {@link #ANSWER}
     * @since 1.0.0
     */
    public static Type byName(final String name) {
      return EnumUtil.constantByNameOrElse(Answer.Type.class, name, ANSWER);
    }
  }
}
