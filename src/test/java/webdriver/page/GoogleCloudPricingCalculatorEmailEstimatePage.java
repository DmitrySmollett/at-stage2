package webdriver.page;

import static webdriver.base.JSHelpers.openLinkInNewTab;
import static webdriver.base.PageHelpers.forceClickWhenClickable;
import static webdriver.base.PageHelpers.switchToTheFrame;
import static webdriver.base.PageHelpers.tinyWait;
import static webdriver.base.PageHelpers.waitThisMuchSecondsUntilClickable;
import static webdriver.base.PageHelpers.waitUntilClickable;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleCloudPricingCalculatorEmailEstimatePage extends AbstractPage {
  private static final String TEMPORARY_EMAIL_URL = "http://temp-mail.org";
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
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage registerNewEmailAddress() {
    waitUntilClickable(driver, calculatorEmailField);
    switchDriverToTheNewTab(TEMPORARY_EMAIL_URL);
    waitUntilClickable(driver, copyEmailAddressButton).click();

    tinyWait(driver); //Additional 300 ms to exclude rare cases when browser switches to the next page before email is in the clipboard
    driver.switchTo().window(calculatorWindow);

    if (!(driver instanceof FirefoxDriver)) {         //TODO Switch to System -> properties -> browser in Framework
      switchToTheFrame(driver, devsiteFrame);
      switchToTheFrame(driver, calculatorFrame);
    }
    return this;
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage confirmEstimateWithTemporaryEmailAddress() {
    waitUntilClickable(driver, calculatorEmailField).sendKeys(Keys.chord(Keys.CONTROL, "v"));
    forceClickWhenClickable(driver, calculatorSendEmailButton);
    return this;
  }

  public String getTotalPriceFromTheEmail() {
    driver.switchTo().window(emailWindow);
    waitThisMuchSecondsUntilClickable(driver, googleMailLink, 60).click(); //Waiting for e-mail
    waitUntilClickable(driver, totalCost);
    return totalCost.getText();
  }

  private void switchDriverToTheNewTab(String link) {
    openLinkInNewTab(driver, link);
    List<String> tabs = new ArrayList<>(driver.getWindowHandles());
    if (driver.getWindowHandle().equals(tabs.get(0))) {
      emailWindow = tabs.get(1);
      calculatorWindow = tabs.get(0);
    } else {
      emailWindow = tabs.get(0);
      calculatorWindow = tabs.get(1);
    }
    driver.switchTo().window(emailWindow);
  }
}
