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
 * Represents an interior result of an overall search result. These are found in related topics and direct results.
 *
 * @since 1.0.0
 */
public class ResultItem extends ApiObject {
  private final String url;
  private final Icon icon;
  private final String html;
  private final String text;
  private final String disambiguationName;

  /**
   * Constructs a new ResultItem with a null disambiguation name.
   *
   * @param url the URL for the result
   * @param icon the icon representing the result
   * @param html the HTML summary of the result
   * @param text the plain text summary of the result
   * @since 1.0.0
   */
  public ResultItem(final String url, final Icon icon, final String html, final String text) {
    this(url, icon, html, text, null);
  }

  /**
   * Constructs a new ResultItem.
   *
   * @param url the URL for the result
   * @param icon the icon representing the result
   * @param html the HTML summary of the result
   * @param text the plain text summary of the result
   * @param disambiguationName if the result is part of a disambiguation result, this is the category
   * @since 1.0.0
   */
  public ResultItem(final String url, final Icon icon, final String html, final String text, final String disambiguationName) {
    this.url = url;
    this.icon = icon;
    this.html = html;
    this.text = text;
    this.disambiguationName = disambiguationName;
  }

  /**
   * Gets the URL for the result.
   *
   * @return the URL
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
   * Gets the {@link Icon} for the result.
   *
   * @return the Icon
   * @since 1.0.0
   */
  public Icon icon() {
    return this.icon;
  }

  /**
   * Whether or not the {@link Icon} exists.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasIcon() {
    return this.icon() != null;
  }

  /**
   * Gets the HTML summary of the result.
   *
   * @return the HTML summary
   * @since 1.0.0
   */
  public String html() {
    return orEmpty(this.html);
  }

  /**
   * Whether or not the HTML summary has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasHtml() {
    return has(this.html);
  }

  /**
   * Gets the plain text summary of the result.
   *
   * @return the plain text summary
   * @since 1.0.0
   */
  public String text() {
    return orEmpty(this.text);
  }

  /**
   * Whether or not the plain text summary has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasText() {
    return has(this.text);
  }

  /**
   * Gets the disambiguation name for the result.
   *
   * @return the disambiguation name
   * @since 1.0.0
   */
  public String disambiguationName() {
    return orEmpty(this.disambiguationName);
  }

  /**
   * Whether or not the disambiguation name has content.
   *
   * @return true or false
   * @since 1.0.0
   */
  public boolean hasDisambiguationName() {
    return has(this.disambiguationName);
  }

  @Override
  public String instantInformation() {
    final StringBuilder builder = new StringBuilder();

    if(this.hasText()) {
      builder.append(this.text()).append(" (");

      if(this.hasUrl()) {
        builder.append(this.url());
      } else {
        builder.append("unknown source");
      }

      builder.append(")");

      if(this.hasDisambiguationName()) {
        builder.append(" {").append(this.disambiguationName()).append("}");
      }
    }

    return builder.length() == 0 ? null : builder.toString();
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof ResultItem)) return false;
    final ResultItem that = (ResultItem) o;
    return Objects.equals(this.url(), that.url()) && Objects.equals(this.icon(), that.icon()) && Objects.equals(this.html(), that.html()) && Objects.equals(this.text(), that.text());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.url(), this.icon(), this.html(), this.text());
  }

  @Override
  public String toString() {
    return "ResultItem{" +
            "url='" + this.url + '\'' +
            ", icon=" + this.icon +
            ", html='" + this.html + '\'' +
            ", text='" + this.text + '\'' +
            ", disambiguationName='" + this.disambiguationName + '\'' +
            '}';
  }
}
