package webdriver.base;

import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {

  public static ExpectedCondition<Boolean> pageIsLoaded () {
    return JSHelpers::pageIsLoaded;
  }
}
