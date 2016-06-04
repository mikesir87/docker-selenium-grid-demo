package io.mikesir87.demo.arquillian;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page fragment for the tag cloud appearing in the sidebar.
 *
 * @author Michael Irwin
 */
public class TagCloudFragment {

  @Drone
  private WebDriver browser;

  @Root
  private WebElement root;

  public TagCloudFragment assertHasTags(String... tags) {
    for (String tag : tags) {
      assertHasTag(tag);
    }
    return this;
  }

  public TagCloudFragment assertHasTag(String tag) {
    for (WebElement tagLink : root.findElements(By.tagName("a"))) {
      if (tagLink.getText().equals(tag))
        return this;
    }
    throw new RuntimeException("Unable to find tag with name: " + tag);
  }

  public TagPage clickTag(String tag) {
    assertHasTag(tag);
    root.findElement(By.linkText(tag)).click();

    TagPage page = Graphene.createPageFragment(TagPage.class,
        browser.findElement(By.tagName("body")));
    page.setTag(tag);
    return page;
  }
}
