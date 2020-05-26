package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinNewPastePage {
  private WebDriver driver;
  private String pasteText;
  private String titleText;

  @FindBy(xpath = "//textarea[@name='paste_code']")
  private WebElement generalInputField;

  @FindBy(xpath = "//a[contains(@href, '/archive/') and @class='buttonsm']")
  private WebElement codeStyle;

  public PastebinNewPastePage(WebDriver driver, String pasteText, String titleText) {
    this.driver = driver;
    this.pasteText = pasteText;
    this.titleText = titleText;
    PageFactory.initElements(driver, this);
  }

  public boolean newPasteMatchesTheChosenOne() {
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.textToBePresentInElement(generalInputField, pasteText));
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.titleContains(titleText));
    new WebDriverWait(driver, 10)
        .until(ExpectedConditions.textToBePresentInElement(codeStyle, "Bash"));
    return true;
  }
}
