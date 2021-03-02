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
 * Represents an abstract summary that provides an overview answer to a query.
 *
 * @since 1.0.0
 */
public final class TopicAbstract extends ApiObject {
  private final String heading;
  private final String html;
  private final String text;
  private final String source;
  private final String url;
  private final String image;

  /**
   * Constructs a new TopicAbstract.
   *
   * @param heading the abstract heading
   * @param html the abstract HTML
   * @param text the abstract text
   * @param source the abstract source (e.g. Wikipedia)
   * @param url the abstract URL
   * @param image the abstract image
   * @since 1.0.0
   */
  public TopicAbstract(final String heading, final String html, final String text, final String source, final String url, final String image) {
    this.heading = heading;
    this.html = html;
    this.text = text;
    this.source = source;
    this.url = url;
    this.image = image;
  }

  /**
   * Gets the abstract heading.
   *
   * @return the abstract heading
   * @since 1.0.0
   */
  public String heading() {
    return orEmpty(this.heading);
  }

  /**
   * Whether or not the abstract heading has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasHeading() {
    return has(this.heading);
  }

  /**
   * Gets the abstract HTML.
   *
   * @return the abstract HTML
   * @since 1.0.0
   */
  public String html() {
    return orEmpty(this.html);
  }

  /**
   * Whether or not the abstract HTML has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasHtml() {
    return has(this.html);
  }

  /**
   * Gets the abstract text.
   *
   * @return the abstract text
   * @since 1.0.0
   */
  public String text() {
    return orEmpty(this.text);
  }

  /**
   * Whether or not the abstract text has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasText() {
    return has(this.text);
  }

  /**
   * Gets the abstract source (e.g. Wikipedia).
   *
   * @return the abstract source
   * @since 1.0.0
   */
  public String source() {
    return orEmpty(this.source);
  }

  /**
   * Whether or not the abstract source has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasSource() {
    return has(this.source);
  }

  /**
   * Gets the abstract URL.
   *
   * @return the abstract URL.
   * @since 1.0.0
   */
  public String url() {
    return orEmpty(this.url);
  }

  /**
   * Whether or not the abstract URL has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasUrl() {
    return has(this.url);
  }

  /**
   * Gets the abstract image.
   *
   * @return the abstract image
   * @since 1.0.0
   */
  public String image() {
    return orEmpty(this.image);
  }

  /**
   * Whether or not the abstract image has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasImage() {
    return has(this.image);
  }

  @Override
  public String instantInformation() {
    final StringBuilder builder = new StringBuilder();

    if(this.hasText()) {
      if(this.hasHeading()) {
        builder.append(this.heading()).append(" - ");
      }

      builder.append(this.text()).append(" (");

      if(this.hasUrl()) {
        builder.append(this.url());
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
    if(o == null || getClass() != o.getClass()) return false;
    final TopicAbstract that = (TopicAbstract) o;
    return Objects.equals(this.heading(), that.heading())
            && Objects.equals(this.html(), that.html())
            && Objects.equals(this.text(), that.text())
            && Objects.equals(this.source(), that.source())
            && Objects.equals(this.url(), that.url())
            && Objects.equals(this.image(), that.image());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.heading(), this.html(), this.text(), this.source(), this.url(), this.image());
  }

  @Override
  public String toString() {
    return "TopicAbstract{" +
            "heading='" + this.heading + '\'' +
            ", html='" + this.html + '\'' +
            ", text='" + this.text + '\'' +
            ", source='" + this.source + '\'' +
            ", url='" + this.url + '\'' +
            ", image='" + this.image + '\'' +
            '}';
  }
}
