package io.mikesir87.demo.arquillian;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * Page fragment representing a "tag page" - all posts for a specific tag.
 *
 * @author Michael Irwin
 */
public class TagPage extends BasePage {

  private String tag;

  public TagPage assertOnPage() {
    assertThat(browser.getTitle(), is(equalTo("mikesir87's musings")));
    assertThat(browser.findElement(By.tagName("h1")).getText(),
        containsString("Posts tagged with " + tag));
    return this;
  }

  public TagPage assertPostTitleExists(String postTitle) {
    for (WebElement element : browser.findElements(By.cssSelector("h1.entry-title"))) {
      if (element.getText().contains(postTitle))
        return this;
    }
    throw new RuntimeException("Unable to find post with title: " + postTitle);
  }

  public void setTag(String tag) {
    this.tag = tag;
  }
}
