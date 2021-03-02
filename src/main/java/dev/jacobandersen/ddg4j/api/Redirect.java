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
 * Represents a !Bang Redirect.
 *
 * @since 1.0.0
 */
public class Redirect extends ApiObject {
  private final String redirectUrl;

  /**
   * Constructs a new Redirect.
   *
   * @param redirectUrl the URL to redirect to
   * @since 1.0.0
   */
  public Redirect(final String redirectUrl) {
    this.redirectUrl = redirectUrl;
  }

  /**
   * Gets the URL to redirect to.
   *
   * @return the URL to redirect to
   * @since 1.0.0
   */
  public String redirectUrl() {
    return orEmpty(this.redirectUrl);
  }

  /**
   * Whether or not the redirect URL has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasRedirectUrl() {
    return has(this.redirectUrl);
  }

  @Override
  public String instantInformation() {
    return this.hasRedirectUrl() ? this.redirectUrl() : null;
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof Redirect)) return false;
    final Redirect redirect = (Redirect) o;
    return Objects.equals(this.redirectUrl(), redirect.redirectUrl());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.redirectUrl());
  }

  @Override
  public String toString() {
    return "Redirect{" +
            "redirectUrl='" + this.redirectUrl + '\'' +
            '}';
  }
}
