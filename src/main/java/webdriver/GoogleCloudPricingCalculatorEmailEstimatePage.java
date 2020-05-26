package webdriver;

import java.util.ArrayList;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorEmailEstimatePage {
  private static final String TEMPORARY_EMAIL = "http://temp-mail.org";
  private WebDriver driver;
  private String calculatorWindow;
  private String emailWindow;

  @FindBy(xpath = "//devsite-iframe/child::*")
  WebElement devsiteFrame;

  @FindBy(xpath = "//iframe[@id='myFrame']")
  WebElement calculatorFrame;

  @FindBy(id = "click-to-copy")
  WebElement copyEmailAddressButton;

  @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
  WebElement calculatorEmailField;

  @FindBy(xpath = "//button[@aria-label='Send Email']")
  WebElement calculatorSendEmailButton;

  @FindBy(xpath = "//*[contains(text() , 'noreply@google.com')]")
  WebElement googleMailLink;

  @FindBy(xpath = "//*[contains(text() , 'Estimated Monthly Cost:')]")
  WebElement totalCost;

  public GoogleCloudPricingCalculatorEmailEstimatePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage registerNewEmailAddress() {
    // Additional WebDriverWait because switching to a new page while content is loading quite often
    // result in a broken page.
    waitUntilElementIsClickable(calculatorEmailField);
    switchDriverToTheNewTab(TEMPORARY_EMAIL);
    waitUntilElementIsClickable(copyEmailAddressButton).click();
    driver.switchTo().window(calculatorWindow);

    if (!(driver instanceof FirefoxDriver)) {
      switchToTheCalculatorFrame();
    }
    return this;
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage
      confirmEstimateWithTemporaryEmailAddress() {
    waitUntilElementIsClickable(calculatorEmailField).sendKeys(Keys.chord(Keys.CONTROL, "v"));
    forceClickElementWhenClickable(calculatorSendEmailButton);
    return this;
  }

  public boolean totalPriceInEmailMatchesTheOriginalOne() {
    driver.switchTo().window(emailWindow);
    waitUntilElementIsClickable(googleMailLink).click();
    waitUntilElementIsClickable(totalCost);
    return totalCost.getText().contains("USD 1,082.77");
  }

  private void switchDriverToTheNewTab(String link) {
    ((JavascriptExecutor) driver).executeScript("window.open(arguments[0])", link);
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    if (driver.getWindowHandle().equals(tabs.get(0))) {
      emailWindow = tabs.get(1);
      calculatorWindow = tabs.get(0);
    } else {
      emailWindow = tabs.get(0);
      calculatorWindow = tabs.get(1);
    }
    driver.switchTo().window(emailWindow);
  }

  private void switchToTheCalculatorFrame() {
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(devsiteFrame));
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
  }

  private void forceClickElementWhenClickable(WebElement element) {
    waitUntilElementIsClickable(element);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  private WebElement waitUntilElementIsClickable(WebElement element) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
  }
}
