package io.mikesir87.demo.arquillian;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * An abstract base page fragment that contains common elements on all pages.
 *
 * @author Michael Irwin
 */
abstract public class BasePage {

  @Drone
  protected WebDriver browser;

  public TagCloudFragment withTagCloud() {
    return Graphene.createPageFragment(TagCloudFragment.class,
        browser.findElement(By.cssSelector("#secondary div.tagcloud")));
  }
}
