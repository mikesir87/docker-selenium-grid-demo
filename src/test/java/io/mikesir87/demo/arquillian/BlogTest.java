package io.mikesir87.demo.arquillian;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import java.net.URL;

/**
 * Another blog test using Arquillian's Drone/Graphene abstraction.
 *
 * @author Michael Irwin
 */
@RunWith(Arquillian.class)
public class BlogTest {

  @Drone
  private WebDriver browser;

  @ArquillianResource
  private URL baseUrl;

  @Test
  public void testBlogLinksUsingFirefox(@InitialPage HomePage homePage) {
    homePage.assertOnPage()
        .assertPostTitleExists("Pushing to ECR Using Jenkins Pipeline Plugin")
        .withPostTeaser("Pushing to ECR Using Jenkins Pipeline Plugin")
          .assertHasCategory("Development")
          .clickMoreButton()
        .assertOnPage("Pushing to ECR Using Jenkins Pipeline Plugin")
        .withTagCloud()
          .assertHasTags("docker", "ecr", "wildfly", "agile")
          .clickTag("docker")
        .assertOnPage()
          .assertPostTitleExists("Pushing to ECR Using Jenkins Pipeline Plugin");
  }
}
