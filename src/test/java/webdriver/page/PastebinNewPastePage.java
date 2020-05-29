package webdriver.page;

import static webdriver.base.PageHelpers.waitUntilClickable;
import static webdriver.base.PageHelpers.waitUntilPageIsLoaded;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PastebinNewPastePage extends AbstractPage {

  @FindBy(xpath = "//textarea[@name='paste_code']")
  private WebElement generalInputField;

  @FindBy(xpath = "//a[contains(@href, '/archive/') and @class='buttonsm']")
  private WebElement codeStyle;

  public PastebinNewPastePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public Map<String, String> getNewPasteKeyData() {
    Map<String, String> map = new HashMap<>();
    waitUntilPageIsLoaded(driver);
    map.put("title", driver.getTitle());
    waitUntilClickable(driver,generalInputField);
    map.put("text", generalInputField.getText());
    waitUntilClickable(driver, codeStyle);
    map.put("style", codeStyle.getText());
    return map;
  }
}
