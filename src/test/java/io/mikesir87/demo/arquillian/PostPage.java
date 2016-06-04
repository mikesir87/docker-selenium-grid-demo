package io.mikesir87.demo.arquillian;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

/**
 * A page fragment for the "post details" page.
 *
 * @author Michael Irwin
 */
public class PostPage extends BasePage {

  public PostPage assertOnPage(String postName) {
    assertThat(browser.getTitle(),
        is(equalTo(postName + " – mikesir87's musings")));
    return this;
  }


}
