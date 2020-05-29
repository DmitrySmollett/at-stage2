package webdriver.base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JSHelpers {
  public static void openLinkInNewTab(WebDriver driver, String link) {
    ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", link);
  }

  public static void clickTheElement (WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }
}
