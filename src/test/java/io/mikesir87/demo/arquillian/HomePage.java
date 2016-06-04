package io.mikesir87.demo.arquillian;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * A page abstraction for the blog's home page
 *
 * @author Michael Irwin
 */
@Location("/")
public class HomePage extends BasePage {

  public HomePage assertOnPage() {
    assertThat(browser.getTitle(), is(equalTo("mikesir87's musings")));
    return this;
  }

  public HomePage assertPostTitleExists(String postTitle) {
    for (WebElement element : browser.findElements(By.cssSelector("h1.entry-title"))) {
      if (element.getText().contains(postTitle))
        return this;
    }
    throw new RuntimeException("Unable to find post with title: " + postTitle);
  }

  public TeaserFragment withPostTeaser(String postTitle) {
    for (WebElement element : browser.findElements(By.cssSelector("article.post"))) {
      if (element.findElement(By.cssSelector("h1.entry-title")).getText().contains(postTitle))
        return Graphene.createPageFragment(TeaserFragment.class, element);
    }
    throw new RuntimeException("Unable to find post with title: " + postTitle);
  }
}
