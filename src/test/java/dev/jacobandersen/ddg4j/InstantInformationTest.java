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
package dev.jacobandersen.ddg4j;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InstantInformationTest {
  @Test
  @DisplayName("A request known to result in a topic abstract should show topic abstract instant information")
  public void testTopicAbstractInstantInformation() {
    assertTrue(
            this.instant("valley forge national park")
                    .startsWith("Valley Forge National Historical Park - Valley Forge National Historical Park is the site of the third winter encampment of the Continental Army during the American Revolutionary War,")
    );
  }

  @Test
  @DisplayName("A request known to result in a list of related topics should show the first related topic's instant information")
  public void testRelatedTopicInstantInformation() {
    assertTrue(
            this.instant("simpsons characters")
                    .startsWith("Apu Nahasapeemapetilon - Apu Nahasapeemapetilon is a recurring character in the American animated television series The Simpsons. He is an Indian immigrant proprietor who runs the Kwik-E-Mart")
    );
  }

  @Test
  @DisplayName("A request known to result in a list of disambiguation topics should show the first disambiguation topic's instant information")
  public void testDisambiguationInstantInformation() {
    assertEquals(this.instant("apple"), "Apple An edible fruit produced by an apple tree. (https://duckduckgo.com/Apple)");
  }

  @Test
  @DisplayName("A request known to redirect to an external website (bang command) should show the redirect URL as its instant information")
  public void testBangRedirectInstantInformation() {
    assertEquals(this.instant("!imdb rushmore"), "https://www.imdb.com/find?s=all&q=rushmore");
  }

  @Test
  @DisplayName("A request known to result in a direct answer to a question should show the answer to the question")
  public void testAnswerInstantInformation() {
    assertTrue(this.instant("what is my ip address").startsWith("[IP] Your IP address is"));
  }

  private String instant(final String query) {
    return DuckDuckGo.search(query).instantInformation();
  }
}
