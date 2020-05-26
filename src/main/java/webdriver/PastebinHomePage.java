package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinHomePage {
  private static final String PASTEBIN_HOME_PAGE = "http://pastebin.com/";
  private String titleText;
  private String pasteText;
  private WebDriver driver;

  @FindBy(name = "paste_code")
  private WebElement generalInputField;

  @FindBy(
      xpath =
          "//*[contains(text(), 'Paste Expiration:')]/parent::*/descendant::span[@class='selection']")
  private WebElement expirationTimeBox;

  @FindBy(
      xpath =
          "//*[contains(text(), 'Syntax Highlighting:')]/parent::*/descendant::span[@class='selection']")
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
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public PastebinHomePage openPage() {
    driver.get(PASTEBIN_HOME_PAGE);
    return this;
  }

  public PastebinHomePage inputGeneralPaste(String paste) {
    this.pasteText = paste;
    waitUntilElementIsClickable(generalInputField).sendKeys(paste);
    return this;
  }

  public PastebinHomePage selectSyntaxStyleAsBash() {
    waitUntilElementIsClickable(syntaxStyleBox).click();
    waitUntilElementIsClickable(syntaxStyleBoxBashOption).click();
    return this;
  }

  public PastebinHomePage selectExpirationTimeAs10Minutes() {
    waitUntilElementIsClickable(expirationTimeBox).click();
    waitUntilElementIsClickable(expirationTimeBoxTenMinutesOption).click();
    return this;
  }

  public PastebinHomePage inputTitle(String title) {
    this.titleText = title;
    waitUntilElementIsClickable(titleInputField).sendKeys(title);
    return this;
  }

  public PastebinNewPastePage sendPaste() {
    waitUntilElementIsClickable(submitPasteButton).click();
    return new PastebinNewPastePage(driver,pasteText, titleText);
  }

  public boolean titleOfCreatedPasteContainsEnteredTitle() {
    waitUntilElementIsClickable(submitPasteButton).click();
    new WebDriverWait(driver, 10).until(ExpectedConditions.titleContains(titleText));
    return true;
  }

  private WebElement waitUntilElementIsClickable(WebElement element) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
  }
}
