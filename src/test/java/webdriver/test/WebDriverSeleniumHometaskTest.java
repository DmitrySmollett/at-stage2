package webdriver.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
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
    assertThat(estimateTotalPriceFromEmail, containsString("USD 1,082.77"));
  }

  @Test(description = "Hurt Me Plenty Test")
  public void googleCloudCalculatorEstimateFieldsContainDataThatWasEnteredPreviously() {
    boolean estimateMatchesTheChosenData =
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
    Assert.assertTrue(
        estimateMatchesTheChosenData, "Estimate fields data doesn't match the original one");
  }

  @Test(description = "I Can Win Test")
  public void pasteCanBeCreated() {
    boolean pageTitleMatchesTheOriginalOne =
        new PastebinHomePage(driver)
            .openPage()
            .inputGeneralPaste("Hello from WebDriver")
            .selectExpirationTimeAs10Minutes()
            .inputTitle("helloweb")
            .titleOfCreatedPasteContainsEnteredTitle();
    Assert.assertTrue(
        pageTitleMatchesTheOriginalOne,
        "Title of a new bastebin page doesn't match the original one");
  }

  @Test(description = "Bring It On Test")
  public void generatedPasteMatchesTheChosenOne() {
    String text =
        "git config --global user.name  \"New Sheriff in Town\"\n"
            + "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n"
            + "git push origin master --force";
    boolean pastebinExactMatch =
        new PastebinHomePage(driver)
            .openPage()
            .inputGeneralPaste(text)
            .selectSyntaxStyleAsBash()
            .selectExpirationTimeAs10Minutes()
            .inputTitle("how to gain dominance among developers")
            .sendPaste()
            .newPasteMatchesTheChosenOne();
    Assert.assertTrue(pastebinExactMatch, "New paste doesn'd match the chosen one");
  }

  @AfterMethod(alwaysRun = true)
  public void closeBrowser() {
    driver.quit();
    driver = null;
  }
}