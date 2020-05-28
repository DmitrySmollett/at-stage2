package webdriver.page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudHomePage extends AbstractPage {
  private static final String CLOUD_GOOGLE_HOME_PAGE_URL = "http://cloud.google.com";
  private static final String SEARCH_QUERY = "Google Cloud Platform Pricing Calculator";

  @FindBy(className = "devsite-search-container")
  WebElement searchButton;

  @FindBy(name = "q")
  WebElement searchInput;

  @FindBy(xpath = "//devsite-iframe/child::*")
  WebElement devsiteFrame;

  @FindBy(xpath = "//iframe[@id='myFrame']")
  WebElement calculatorFrame;

  public GoogleCloudHomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudHomePage openPage() {
    driver.navigate().to(CLOUD_GOOGLE_HOME_PAGE_URL);
    return this;
  }

  public GoogleCloudHomePage searchForGoogleCloudPlatformPricingCalculator() {
    waitUntilElementIsClickable(searchButton).click();
    waitUntilElementIsClickable(searchInput).sendKeys(SEARCH_QUERY + Keys.ENTER);
    return this;
  }

  public GoogleCloudPricingCalculatorPage switchToTheGoogleCloudPlatformPricingCalculatorPage() {
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(
            ExpectedConditions.elementToBeClickable(
                By.xpath(
                    "//div[@class='gsc-thumbnail-inside']/descendant::b[text()='"
                        + SEARCH_QUERY
                        + "']")))
        .click();
    switchToTheCalculatorFrame();
    return new GoogleCloudPricingCalculatorPage(driver);
  }

  private void switchToTheCalculatorFrame() {
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(devsiteFrame));
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(calculatorFrame));
  }

  private WebElement waitUntilElementIsClickable(WebElement element) {
    return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.elementToBeClickable(element));
  }
}
