package webdriver.page;

import static webdriver.base.PageHelpers.targetClickWithOffsetWhenClickable;
import static webdriver.base.PageHelpers.waitUntilClickable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage extends AbstractPage {
  private static final String PASTEBIN_HOME_PAGE_URL = "http://pastebin.com/";
  private String titleText;
  private String pasteText;

  @FindBy(name = "paste_code")
  private WebElement generalInputField;

  @FindBy(xpath = "//*[contains(text(), 'Paste Expiration:')]/parent::*/descendant::span[@class='selection']")
  private WebElement expirationTimeBox;

  @FindBy(xpath ="//*[contains(text(), 'Syntax Highlighting:')]/parent::*/descendant::span[@class='selection']")
  private WebElement syntaxStyleBox;

  @FindBy(xpath = "//li[contains(text(), '10 Minutes')]")
  private WebElement expirationTimeBoxTenMinutesOption;

  @FindBy(xpath = "//li[contains(text(), 'Bash')]")
  private WebElement syntaxStyleBoxBashOption;

  @FindBy(name = "paste_name")
  private WebElement titleInputField;

  @FindBy(name = "submit")
  private WebElement submitPasteButton;

  public PastebinHomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public PastebinHomePage openPage() {
    driver.get(PASTEBIN_HOME_PAGE_URL);
    return this;
  }

  public PastebinHomePage inputGeneralPaste(String paste) {
    this.pasteText = paste;
    waitUntilClickable(driver, generalInputField).sendKeys(paste);
    return this;
  }

  public PastebinHomePage selectSyntaxStyleAsBash() {
    targetClickWithOffsetWhenClickable(driver, syntaxStyleBox);
    waitUntilClickable(driver, syntaxStyleBoxBashOption).click();
    return this;
  }

  public PastebinHomePage selectExpirationTimeAs10Minutes() {
    targetClickWithOffsetWhenClickable(driver, expirationTimeBox);
    waitUntilClickable(driver, expirationTimeBoxTenMinutesOption).click();
    return this;
  }

  public PastebinHomePage inputTitle(String title) {
    this.titleText = title;
    waitUntilClickable(driver, titleInputField).sendKeys(title);
    return this;
  }

  public PastebinNewPastePage sendPaste() {
    waitUntilClickable(driver, submitPasteButton).click();
    return new PastebinNewPastePage(driver, pasteText, titleText);
  }

  public boolean titleOfCreatedPasteContainsEnteredTitle() {
    waitUntilClickable(driver, submitPasteButton).click();
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.titleContains(titleText));
    return true;
  }
}
