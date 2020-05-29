package webdriver.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import webdriver.page.GoogleCloudHomePage;
import webdriver.page.PastebinHomePage;

public class WebDriverSeleniumHometaskTest {
  private WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void browserSetup() {
    driver = new ChromeDriver();
    //driver = new FirefoxDriver();
    //driver = new OperaDriver();
  }

  @Test(description = "Hardcore Test")
  public void googleCloudCalculatorEstimateTotalPriceMatchesTheOneFromEmail() {
    String estimateTotalPriceFromEmail =
        new GoogleCloudHomePage(driver)
            .openPage()
            .searchForGoogleCloudPlatformPricingCalculator()
            .switchToTheGoogleCloudPlatformPricingCalculatorPage()
            .clickComputeEngineButton()
            .inputNumberOfInstances(4)
            .pickFreeOperatingSystem()
            .pickRegularMachineClass()
            .pickStandardEightMachineType()
            .addGpus()
            .pickLocalSsd()
            .pickDatacenterLocation()
            .pickCommittedUsage()
            .addToEstimate()
            .clickEmailEstimate()
            .registerNewEmailAddress()
            .confirmEstimateWithTemporaryEmailAddress()
            .getTotalPriceFromTheEmail();
    assertThat(estimateTotalPriceFromEmail).as("Total price from e-mail").contains("USD 1,082.77");
  }

  @Test(description = "Hurt Me Plenty Test")
  public void googleCloudCalculatorEstimateFieldsContainDataThatWasEnteredPreviously() {
    Map<String, String> estimateData =
        new GoogleCloudHomePage(driver)
            .openPage()
            .searchForGoogleCloudPlatformPricingCalculator()
            .switchToTheGoogleCloudPlatformPricingCalculatorPage()
            .clickComputeEngineButton()
            .inputNumberOfInstances(4)
            .pickFreeOperatingSystem()
            .pickRegularMachineClass()
            .pickStandardEightMachineType()
            .addGpus()
            .pickLocalSsd()
            .pickDatacenterLocation()
            .pickCommittedUsage()
            .addToEstimate()
            .checkEstimateFields();
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(estimateData.get("VM Class")).as("Virtual machine class").contains("regular");
    softly.assertThat(estimateData.get("Instance Type")).as("Instance type").contains("n1-standard-8");
    softly.assertThat(estimateData.get("Region")).as("Region").contains("Frankfurt");
    softly.assertThat(estimateData.get("Local SSD")).as("Local SSD space").contains("2x375 GiB");
    softly.assertThat(estimateData.get("Commitment")).as("Commitment term").contains("1 Year");
    softly.assertThat(estimateData.get("Total Cost")).as("Estimate total cost").contains("USD 1,082.77 per 1 month");
    softly.assertAll();
  }

  @Test(description = "I Can Win Test")
  public void pasteCanBeCreated() {
    String pageTitle =
        new PastebinHomePage(driver)
            .openPage()
            .inputGeneralPaste("Hello from WebDriver")
            .selectExpirationTimeAs10Minutes()
            .inputTitle("helloweb")
            .getPageTitle();
    assertThat(pageTitle).as("Page title").contains("helloweb");
  }

  @Test(description = "Bring It On Test")
  public void generatedPasteMatchesTheChosenOne() {
    String text =
        "git config --global user.name  \"New Sheriff in Town\"\n"
            + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
            + "git push origin master --force";
    Map<String, String> newPaste =
        new PastebinHomePage(driver)
            .openPage()
            .inputGeneralPaste(text)
            .selectSyntaxStyleAsBash()
            .selectExpirationTimeAs10Minutes()
            .inputTitle("how to gain dominance among developers")
            .sendPaste()
            .getNewPasteKeyData();
    SoftAssertions softly = new SoftAssertions();
    softly.assertThat(newPaste.get("title")).as("Paste title").contains("how to gain dominance among developers");
    softly.assertThat(newPaste.get("text")).as("Paste text").contains(text);
    softly.assertThat(newPaste.get("style")).as("Paste style").contains("Bash");
    softly.assertAll();
  }

  @AfterMethod(alwaysRun = true)
  public void closeBrowser() {
    driver.quit();
    driver = null;
  }
}
