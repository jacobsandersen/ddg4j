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
 * Represents icon information that is attached to some other {@link ApiObject}s.
 *
 * @since 1.0.0
 */
public class Icon extends ApiObject {
  private final String url;
  private final int height;
  private final int width;

  /**
   * Constructs a new Icon.
   *
   * @param url the icon URL (relative to DuckDuckGo's website)
   * @param height the icon height in pixels
   * @param width the icon width in pixels
   * @since 1.0.0
   */
  public Icon(final String url, final int height, final int width) {
    this.url = url;
    this.height = height;
    this.width = width;
  }

  /**
   * Gets the URL of the icon relative to DuckDuckGo's website.
   *
   * @return the url of the icon
   * @since 1.0.0
   */
  public String url() {
    return orEmpty(this.url);
  }

  /**
   * Whether or not the URL has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasUrl() {
    return has(this.url);
  }

  /**
   * Gets the height of the icon in pixels.
   *
   * @return the height of the icon
   * @since 1.0.0
   */
  public int height() {
    return this.height;
  }

  /**
   * Whether or not the height of the icon is actually set (greater than 0).
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasHeight() {
    return this.height > 0;
  }

  /**
   * Gets the width of the icon in pixels.
   *
   * @return the width of the icon
   * @since 1.0.0
   */
  public int width() {
    return this.width;
  }

  /**
   * Whether or not the width of the icon is actually set (greater than 0).
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasWidth() {
    return this.width > 0;
  }

  @Override
  public String instantInformation() {
    return this.hasUrl() ? this.url() : null;
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof Icon)) return false;
    final Icon icon = (Icon) o;
    return this.height() == icon.height()
            && this.width() == icon.width()
            && Objects.equals(this.url(), icon.url());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.url(), this.height(), this.width());
  }

  @Override
  public String toString() {
    return "Icon{" +
            "url='" + this.url + '\'' +
            ", height=" + this.height +
            ", width=" + this.width +
            '}';
  }
}
