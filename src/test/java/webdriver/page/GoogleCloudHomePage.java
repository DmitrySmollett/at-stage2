package webdriver.page;

import static webdriver.base.PageHelpers.switchToTheFrame;
import static webdriver.base.PageHelpers.waitUntilClickable;
import static webdriver.base.PageHelpers.waitUntilClickableByXpath;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    waitUntilClickable(driver, searchButton).click();
    waitUntilClickable(driver, searchInput).sendKeys(SEARCH_QUERY + Keys.ENTER);
    return this;
  }

  public GoogleCloudPricingCalculatorPage switchToTheGoogleCloudPlatformPricingCalculatorPage() {
    waitUntilClickableByXpath(driver, getXpathForQuery(SEARCH_QUERY)).click();
    switchToTheFrame(driver, devsiteFrame);
    switchToTheFrame(driver, calculatorFrame);
    return new GoogleCloudPricingCalculatorPage(driver);
  }

  private String getXpathForQuery(String query) {
    return "//div[@class='gsc-thumbnail-inside']/descendant::b[text()='" + query + "']";
  }
}
