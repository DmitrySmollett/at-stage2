package webdriver.page;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage
{
  protected WebDriver driver;

  protected final int WAIT_TIMEOUT_SECONDS = 10; //TODO Switch to System -> properties -> wait_timeout in Framework

  protected AbstractPage(WebDriver driver)
  {
    this.driver = driver;
  }

}