package io.mikesir87.demo.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Sample test using the Selenium API directly.
 *
 * @author Michael Irwin
 */
public class BlogTest {
  private RemoteWebDriver driver;

  @Before
  public void setUp() throws Exception {
    driver = new RemoteWebDriver(new URL("http://hub:4444/wd/hub"),
        DesiredCapabilities.firefox());
  }

  @Test
  public void testVariousBlogLinks() throws Exception {
    driver.get("https://blog.mikesir87.io/");
    assertThat(driver.getTitle(), is("mikesir87's musings"));
    driver.findElementByPartialLinkText("Pushing to ECR Using Jenkins Pipeline Plugin").click();

    assertThat(driver.getTitle(), is("Pushing to ECR Using Jenkins Pipeline Plugin – mikesir87's musings"));
    driver.findElementByCssSelector("#tag-cloud-widget > div.tagcloud > a[title=\"Posts tagged docker\"]").click();
    assertThat(driver.getTitle(), is("mikesir87's musings"));
    assertThat(driver.findElementByTagName("h1").getText(), is("Posts tagged with docker"));
    driver.findElementByPartialLinkText("Using Docker for QA Testing").click();

    assertThat(driver.getTitle(), is("Using Docker for QA Testing – mikesir87's musings"));
  }

  @After
  public void tearDown() throws Exception {
    driver.close();
  }
}
