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

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import dev.jacobandersen.ddg4j.api.Answer;
import dev.jacobandersen.ddg4j.api.Definition;
import dev.jacobandersen.ddg4j.api.Icon;
import dev.jacobandersen.ddg4j.api.Redirect;
import dev.jacobandersen.ddg4j.api.ResultItem;
import dev.jacobandersen.ddg4j.api.SearchResult;
import dev.jacobandersen.ddg4j.api.TopicAbstract;
import dev.jacobandersen.ddg4j.api.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles deserialization of the raw responses from DuckDuckGo.
 *
 * @since 1.0.0
 */
public class SearchResultDeserializer extends StdDeserializer<SearchResult> {
  private static final long serialVersionUID = 99482938294345242L;

  /**
   * Constructs a new SearchResultDeserializer.
   *
   * @since 1.0.0
   */
  public SearchResultDeserializer() {
    super(SearchResult.class);
  }

  @Override
  public SearchResult deserialize(final JsonParser parser, final DeserializationContext ctx) throws IOException {
    final JsonNode node = parser.getCodec().readTree(parser);

    final Type type = Type.byName(node.get("Type").asText(""));
    final List<ResultItem> relatedTopics = this.deserializeResultItems(node.get("RelatedTopics"));
    final List<ResultItem> results = this.deserializeResultItems(node.get("Results"));

    return SearchResult.builder(type)
            .topicAbstract(new TopicAbstract(
                    node.get("Heading").asText(""),
                    node.get("Abstract").asText(""),
                    node.get("AbstractText").asText(""),
                    node.get("AbstractSource").asText(""),
                    node.get("AbstractURL").asText(""),
                    node.get("Image").asText("")
            ))
            .answer(new Answer(
                    node.get("Answer").asText(""),
                    Answer.Type.byName(node.get("AnswerType").asText(""))
            ))
            .definition(new Definition(
                    node.get("Definition").asText(""),
                    node.get("DefinitionSource").asText(""),
                    node.get("DefinitionURL").asText("")
            ))
            .relatedTopics(relatedTopics)
            .results(results)
            .redirect(new Redirect(node.get("Redirect").asText("")))
            .build();
  }

  private List<ResultItem> deserializeResultItems(final JsonNode node) {
    final List<ResultItem> items = new ArrayList<>();

    if(node.isArray()) {
      for(final JsonNode inner : node) {
        if(inner.has("Name") && inner.has("Topics")) {
          final JsonNode topics = inner.get("Topics");
          if(topics.isArray()) {
            for(final JsonNode topic : topics) {
              items.add(this.deserializeResultItem(topic));
            }
          }
        } else {
          items.add(this.deserializeResultItem(inner));
        }
      }
    }

    return items;
  }

  private ResultItem deserializeResultItem(final JsonNode node) {
    final JsonNode iconNode = node.has("Icon") ? node.get("Icon") : null;

    final Icon icon;
    if(iconNode != null) {
      icon = new Icon(
              iconNode.get("URL").asText(""),
              iconNode.get("Height").asInt(-1),
              iconNode.get("Width").asInt(-1)
      );
    } else {
      icon = new Icon("", -1, -1);
    }

    return new ResultItem(
            node.get("FirstURL").asText(""),
            icon,
            node.get("Result").asText(""),
            node.get("Text").asText("")
    );
  }
}
