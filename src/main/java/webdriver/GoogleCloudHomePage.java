package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage {
  private static final String CLOUD_GOOGLE_HOME_PAGE = "http://cloud.google.com/";
  private static final String SEARCH_QUERRY = "Google Cloud Platform Pricing Calculator";
  private WebDriver driver;

  @FindBy(className = "devsite-search-form")
  WebElement searchButton;

  @FindBy(name = "q")
  WebElement searchInput;

  @FindBy(xpath = "//devsite-iframe/child::*")
  WebElement devsiteFrame;

  @FindBy(xpath = "//iframe[@id='myFrame']")
  WebElement calculatorFrame;

  public GoogleCloudHomePage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudHomePage openPage() {
    driver.get(CLOUD_GOOGLE_HOME_PAGE);
    return this;
  }

  public GoogleCloudHomePage searchForGoogleCloudPlatformPricingCalculator() {
    waitUntilElementIsClickable(searchButton).click();
    waitUntilElementIsClickable(searchInput).sendKeys(SEARCH_QUERRY + Keys.ENTER);
    return this;
  }

  public GoogleCloudPricingCalculatorPage switchToTheGoogleCloudPlatformPricingCalculatorPage() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(
        By.xpath(
            "//div[@class='gsc-thumbnail-inside']/descendant::b[text()='" + SEARCH_QUERRY + "']")))
        .click();
    switchToTheCalculatorFrame();
    return new GoogleCloudPricingCalculatorPage(driver);
  }

  private void switchToTheCalculatorFrame() {
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(devsiteFrame));
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
  }

  private WebElement waitUntilElementIsClickable(WebElement element) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
  }
}
