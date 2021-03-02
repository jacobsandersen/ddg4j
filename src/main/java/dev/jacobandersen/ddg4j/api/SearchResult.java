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

import dev.jacobandersen.ddg4j.util.IllegalIndexException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Represents a full DuckDuckGo search result in response to a query.
 *
 * @since 1.0.0
 */
public final class SearchResult {
  private final Type type;
  private TopicAbstract topicAbstract;
  private Answer answer;
  private Definition definition;
  private final List<ResultItem> relatedTopics;
  private final List<ResultItem> results;
  private Redirect redirect;

  private SearchResult(final Type type) {
    this.type = type;
    this.relatedTopics = new ArrayList<>();
    this.results = new ArrayList<>();
  }

  /**
   * Gets the {@link Type} of the search result.
   *
   * @return the type
   * @since 1.0.0
   */
  public Type type() {
    return this.type;
  }

  /**
   * Gets the abstract summary of the search result.
   *
   * @return the abstract summary
   * @since 1.0.0
   */
  public TopicAbstract topicAbstract() {
    return this.topicAbstract;
  }

  /**
   * Sets the abstract summary of the search result.
   *
   * @param topicAbstract the abstract summary
   * @since 1.0.0
   */
  public void topicAbstract(final TopicAbstract topicAbstract) {
    this.topicAbstract = topicAbstract;
  }

  /**
   * Gets the answer component of the search result.
   *
   * @return the answer component
   * @since 1.0.0
   */
  public Answer answer() {
    return this.answer;
  }

  /**
   * Sets the answer component of the search result.
   *
   * @param answer the answer component
   * @since 1.0.0
   */
  public void answer(final Answer answer) {
    this.answer = answer;
  }

  /**
   * Gets the definition component of the search result.
   *
   * @return the definition component
   * @since 1.0.0
   */
  public Definition definition() {
    return this.definition;
  }

  /**
   * Sets the definition component of the search result.
   *
   * @param definition the definition component
   * @since 1.0.0
   */
  public void definition(final Definition definition) {
    this.definition = definition;
  }

  /**
   * Gets the related topics of the search result.
   *
   * @return the related topics
   * @since 1.0.0
   */
  public List<ResultItem> relatedTopics() {
    return this.relatedTopics;
  }

  /**
   * Sets the related topics of the search result.
   *
   * @param relatedTopics the related topics
   * @since 1.0.0
   */
  public void relatedTopics(final List<ResultItem> relatedTopics) {
    this.relatedTopics.clear();
    this.addRelatedTopics(relatedTopics);
  }

  /**
   * Adds a single related topic to the related topics of the search result.
   *
   * @param relatedTopic the related topic to add
   * @since 1.0.0
   */
  public void addRelatedTopic(final ResultItem relatedTopic) {
    this.relatedTopics.add(relatedTopic);
  }

  /**
   * Adds many related topics to the related topics of the search result.
   *
   * @param relatedTopics the related topics to add
   * @since 1.0.0
   */
  public void addRelatedTopics(final Collection<ResultItem> relatedTopics) {
    this.relatedTopics.addAll(relatedTopics);
  }

  /**
   * Gets the internal results of the search result.
   *
   * @return the results
   * @since 1.0.0
   */
  public List<ResultItem> results() {
    return this.results;
  }

  /**
   * Sets the internal results of the search result.
   *
   * @param results the results
   * @since 1.0.0
   */
  public void results(final List<ResultItem> results) {
    this.results.clear();
    this.addResults(results);
  }

  /**
   * Adds a single result to the internal results of the search result.
   *
   * @param result the result to add
   * @since 1.0.0
   */
  public void addResult(final ResultItem result) {
    this.results.add(result);
  }

  /**
   * Adds many results to the internal results of the search result.
   *
   * @param results the results to add
   * @since 1.0.0
   */
  public void addResults(final Collection<ResultItem> results) {
    this.results.addAll(results);
  }

  /**
   * Gets the redirect component of the search result.
   *
   * @return the redirect component
   * @since 1.0.0
   */
  public Redirect redirect() {
    return this.redirect;
  }

  /**
   * Sets the redirect component of the search result.
   *
   * @param redirect the redirect component
   * @since 1.0.0
   */
  public void redirect(final Redirect redirect) {
    this.redirect = redirect;
  }

  /**
   * Tries to get the best possible answer to the query for a single reply.
   * <p>Follows this path: Answer, Abstract, Related[0], Definition, and Bang Redirects.</p>
   *
   * @return the best possible answer or "Sorry, no results."
   * @see #instantInformation(String[]) the parent method with adjustable priorities.
   * @since 1.0.0
   */
  public final String instantInformation() {
    return this.instantInformation(new String[]{"answer", "abstract", "related.0", "definition", "redirect"});
  }

  /**
   * Tries to get the best possible answer to the query for a single reply. Only replies in plain text.
   * <p>Follows the given path of fields to check. Indexable fields may be specified with key.index notation.</p>
   *
   * @param priorities the list of fields to check for a viable answer
   * @return the best possible answer or "Sorry, no results."
   * @since 1.0.0
   */
  public final String instantInformation(final String[] priorities) {
    for(final String priority : priorities) {
      final String[] prioritySplit = priority.split("\\.");

      final String field = prioritySplit[0];
      int index = -1;

      final String indexStr = prioritySplit.length > 1 ? prioritySplit[1] : null;
      if(indexStr != null) {
        try {
          index = Integer.parseInt(indexStr);
          if(index < 0) {
            throw new IllegalIndexException(indexStr);
          }
        } catch(final NumberFormatException ignored) {
        }
      }

      String instant = null;

      switch (field) {
        case "abstract":
          if(this.topicAbstract != null) {
            instant = this.topicAbstract.instantInformation();
            break;
          }
          break;
        case "answer":
          if(this.answer != null) {
            instant = this.answer.instantInformation();
            break;
          }
          break;
        case "definition":
          if(this.definition != null) {
            instant = this.definition.instantInformation();
            break;
          }
          break;
        case "related":
          if(index >= 0 && this.relatedTopics.size() > index) {
            instant = this.relatedTopics.get(index).instantInformation();
            break;
          }
          break;
        case "result":
          if(index >= 0 && this.results.size() > index) {
            instant = this.results.get(index).instantInformation();
            break;
          }
          break;
        case "redirect":
          if(this.redirect != null) {
            instant = this.redirect.instantInformation();
            break;
          }
          break;
        default:
          break;
      }

      if(instant != null) {
        return instant;
      }
    }

    return "Sorry, no results.";
  }

  /**
   * Creates a new search result builder to easily create a new search result.
   *
   * @param type the search result {@link Type}
   * @return the search result builder
   * @since 1.0.0
   */
  public static Builder builder(final Type type) {
    return new Builder(type);
  }

  @Override
  public boolean equals(final Object o) {
    if(this == o) return true;
    if(!(o instanceof SearchResult)) return false;
    final SearchResult that = (SearchResult) o;
    return this.type() == that.type()
            && Objects.equals(this.topicAbstract(), that.topicAbstract())
            && Objects.equals(this.answer(), that.answer())
            && Objects.equals(this.definition(), that.definition())
            && Objects.equals(this.redirect(), that.redirect())
            && Objects.equals(this.relatedTopics(), that.relatedTopics())
            && Objects.equals(this.results(), that.results());
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.type(), this.topicAbstract(), this.answer(), this.definition(), this.redirect(), this.relatedTopics(), this.results());
  }

  @Override
  public String toString() {
    return "SearchResult{" +
            "type=" + this.type +
            ", topicAbstract=" + this.topicAbstract +
            ", answer=" + this.answer +
            ", definition=" + this.definition +
            ", relatedTopics=" + this.relatedTopics +
            ", results=" + this.results +
            ", redirect=" + this.redirect +
            '}';
  }

  /**
   * Provides utilities for building {@link SearchResult}s.
   *
   * @since 1.0.0
   */
  public static final class Builder {
    private final SearchResult result;

    /**
     * Constructs a new search result builder.
     *
     * @param type the search result {@link Type}
     * @since 1.0.0
     */
    public Builder(final Type type) {
      this.result = new SearchResult(type);
    }

    /**
     * Sets the abstract summary of the result.
     *
     * @param topicAbstract the abstract summary
     * @return this builder
     * @since 1.0.0
     */
    public Builder topicAbstract(final TopicAbstract topicAbstract) {
      this.result.topicAbstract(topicAbstract);
      return this;
    }

    /**
     * Sets the answer component of the result.
     *
     * @param answer the answer component
     * @return this builder
     * @since 1.0.0
     */
    public Builder answer(final Answer answer) {
      this.result.answer(answer);
      return this;
    }

    /**
     * Sets the definition component of the result.
     *
     * @param definition the definition component
     * @return this builder
     * @since 1.0.0
     */
    public Builder definition(final Definition definition) {
      this.result.definition(definition);
      return this;
    }

    /**
     * Adds a related topic to the result.
     *
     * @param item the related topic to add
     * @return this builder
     * @since 1.0.0
     */
    public Builder relatedTopic(final ResultItem item) {
      this.result.addRelatedTopic(item);
      return this;
    }

    /**
     * Adds many related topics to the result.
     *
     * @param item the related topics to add
     * @return this builder
     * @since 1.0.0
     */
    public Builder relatedTopics(final List<ResultItem> item) {
      return this.relatedTopics(item, false);
    }

    /**
     * Adds many related topics to the result, optionally overwriting what is already there.
     *
     * @param relatedTopics the related topics to add
     * @param overwrite whether or not to overwrite
     * @return this builder
     * @since 1.0.0
     */
    public Builder relatedTopics(final List<ResultItem> relatedTopics, final boolean overwrite) {
      if(overwrite) {
        this.result.relatedTopics(relatedTopics);
      } else {
        this.result.addRelatedTopics(relatedTopics);
      }

      return this;
    }

    /**
     * Adds an internal result to the result.
     *
     * @param result the internal result to add
     * @return this builder
     * @since 1.0.0
     */
    public Builder result(final ResultItem result) {
      this.result.addResult(result);
      return this;
    }

    /**
     * Adds many internal results to the result.
     *
     * @param results the internal results to add
     * @return this builder
     * @since 1.0.0
     */
    public Builder results(final List<ResultItem> results) {
      return this.results(results, false);
    }

    /**
     * Adds many internal results to the result, optionally overwriting what is already there.
     *
     * @param results the internal results to add
     * @param overwrite whether or not to overwrite
     * @return this builder
     * @since 1.0.0
     */
    public Builder results(final List<ResultItem> results, final boolean overwrite) {
      if(overwrite) {
        this.result.results(results);
      } else {
        this.result.addResults(results);
      }

      return this;
    }

    /**
     * Sets the redirect component of the result.
     *
     * @param redirect the redirect component
     * @return this builder
     * @since 1.0.0
     */
    public Builder redirect(final Redirect redirect) {
      this.result.redirect(redirect);
      return this;
    }

    /**
     * Returns the {@link SearchResult} that this builder has built.
     *
     * @return the search result
     * @since 1.0.0
     */
    public SearchResult build() {
      return this.result;
    }
  }
}
