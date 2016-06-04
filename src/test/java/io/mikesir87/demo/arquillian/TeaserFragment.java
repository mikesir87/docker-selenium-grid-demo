package io.mikesir87.demo.arquillian;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.fragment.Root;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

/**
 * Page fragment representing an individual "teaser view" on the blog home page.
 *
 * @author Michael Irwin
 */
public class TeaserFragment {

  @Drone
  private WebDriver driver;

  @Root
  private WebElement root;

  @FindBy(css = "span.cat-links")
  private WebElement categoryDisplay;

  @FindBy(css = ".btn.btn-default.read-more")
  private WebElement moreButton;

  public TeaserFragment assertHasCategory(String categoryName) {
    assertThat(categoryDisplay.getText(), containsString(categoryName));
    return this;
  }

  public PostPage clickMoreButton() {
    moreButton.click();

    return Graphene.createPageFragment(PostPage.class,
        driver.findElement(By.tagName("body")));
  }

}
