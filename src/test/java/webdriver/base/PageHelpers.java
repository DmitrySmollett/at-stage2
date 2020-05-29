package webdriver.base;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageHelpers {
  private static final int WAIT_TIMEOUT_SECONDS = 10; //TODO Switch to System -> properties -> wait_timeout in Framework

  public static WebElement waitUntilClickable(WebDriver driver, WebElement element) {
    return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.elementToBeClickable(element));
  }

  public static WebElement waitThisMuchSecondsUntilClickable(WebDriver driver, WebElement element, int timeout) {
    return new WebDriverWait(driver, timeout)
        .until(ExpectedConditions.elementToBeClickable(element));
  }

  public static WebElement waitUntilClickableByXpath(WebDriver driver, String xpath) {
    return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
  }

  public static void forceClickWhenClickable(WebDriver driver, WebElement element) {
    waitUntilClickable(driver, element);
    JSHelpers.clickTheElement(driver,element);
  }

  public static void targetClickWithOffsetWhenClickable(WebDriver driver, WebElement element) {
    waitUntilClickable(driver, element);
    new Actions(driver).moveToElement(element, 10, 10).click().perform();
  }

  public static void switchToTheFrame(WebDriver driver, WebElement frame) {
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
  }

  // Only for extreme/rare scenarios
  public static void tinyWait(WebDriver driver) {
    new Actions(driver).pause(Duration.ofMillis(300)).perform();
  }
}
