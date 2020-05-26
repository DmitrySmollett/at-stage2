package webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage {
  private WebDriver driver;

  @FindBy(
      xpath =
          "//md-pagination-wrapper/descendant::div[@title='Compute Engine' and @class='tab-holder compute']")
  private WebElement computeEngineButton;

  @FindBy(id = "input_58")
  private WebElement numberOfInstancesField;

  @FindBy(id = "select_70")
  private WebElement operatingSystemField;

  @FindBy(id = "select_option_60")
  private WebElement operatingSystemFreeSoftwareOption;

  @FindBy(id = "select_value_label_52")
  private WebElement machineClassField;

  @FindBy(id = "select_option_72")
  private WebElement machineClassRegularOption;

  @FindBy(id = "select_83")
  private WebElement machineTypeField;

  @FindBy(id = "select_option_212")
  private WebElement machineTypeStandardEightOption;

  @FindBy(xpath = "//*[contains(text(), 'Add GPUs')]/parent::*")
  private WebElement addGpusCheckbox;

  @FindBy(id = "select_334")
  private WebElement numberOfGpusField;

  @FindBy(id = "select_option_339")
  private WebElement numberOfGpusSingleGpuOption;

  @FindBy(id = "select_336")
  private WebElement gpuTypeField;

  @FindBy(id = "select_option_346")
  private WebElement gpuTypeChooseGpuNVIDIATeslaV100Type;

  @FindBy(id = "select_170")
  private WebElement localSsdField;

  @FindBy(id = "select_option_233")
  private WebElement localSsd2x375Option;

  @FindBy(id = "select_85")
  private WebElement datacenterLocationField;

  @FindBy(id = "select_option_181")
  private WebElement datacenterLocationFrankfurtOption;

  @FindBy(id = "select_92")
  private WebElement committedUsageField;

  @FindBy(id = "select_option_90")
  private WebElement committedUsageOneYearOption;

  @FindBy(
      xpath =
          "//form[@name='ComputeEngineForm']/descendant::*[contains(text(), 'Add to Estimate')]")
  private WebElement addToEstimateButton;

  @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'VM class:')]")
  private WebElement estimateVmClass;

  @FindBy(
      xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Instance type:')]")
  private WebElement estimateInstanceType;

  @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Region:')]")
  private WebElement estimateRegion;

  @FindBy(
      xpath =
          "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Total available local SSD space')]")
  private WebElement estimateLocalSsdSpace;

  @FindBy(
      xpath =
          "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Commitment term:')]")
  private WebElement estimateCommitmentTerm;

  @FindBy(xpath = "//b[@class='ng-binding' and contains(text(), 'Total Estimated Cost')]")
  private WebElement estimateTotalCost;

  @FindBy(xpath = "//button[@id='email_quote']")
  private WebElement emailEstimateButton;

  public GoogleCloudPricingCalculatorPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudPricingCalculatorPage clickComputeEngineButton() {
    waitUntilElementIsClickable(computeEngineButton).click();
    return this;
  }

  public GoogleCloudPricingCalculatorPage inputNumberOfInstances(Integer numberOfInstances) {
    waitUntilElementIsClickable(numberOfInstancesField).sendKeys(numberOfInstances.toString());
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickFreeOperatingSystem() {
    forceClickElementWhenClickable(operatingSystemField);
    forceClickElementWhenClickable(operatingSystemFreeSoftwareOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickRegularMachineClass() {
    forceClickElementWhenClickable(machineClassField);
    forceClickElementWhenClickable(machineClassRegularOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickStandardEightMachineType() {
    forceClickElementWhenClickable(machineTypeField);
    forceClickElementWhenClickable(machineTypeStandardEightOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage addGpus() {
    forceClickElementWhenClickable(addGpusCheckbox);
    forceClickElementWhenClickable(numberOfGpusField);
    forceClickElementWhenClickable(numberOfGpusSingleGpuOption);
    forceClickElementWhenClickable(gpuTypeField);
    forceClickElementWhenClickable(gpuTypeChooseGpuNVIDIATeslaV100Type);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickLocalSsd() {
    forceClickElementWhenClickable(localSsdField);
    forceClickElementWhenClickable(localSsd2x375Option);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickDatacenterLocation() {
    forceClickElementWhenClickable(datacenterLocationField);
    forceClickElementWhenClickable(datacenterLocationFrankfurtOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickCommittedUsage() {
    forceClickElementWhenClickable(committedUsageField);
    forceClickElementWhenClickable(committedUsageOneYearOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage addToEstimate() {
    forceClickElementWhenClickable(addToEstimateButton);
    return this;
  }

  public boolean checkEstimateFields() {
    new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfAllElements(
        estimateVmClass,
        estimateInstanceType,
        estimateRegion,
        estimateLocalSsdSpace,
        estimateCommitmentTerm,
        estimateTotalCost));
    return estimateVmClass.getText().contains("regular")
        && estimateInstanceType.getText().contains("n1-standard-8")
        && estimateRegion.getText().contains("Frankfurt")
        && estimateLocalSsdSpace.getText().contains("2x375 GiB")
        && estimateCommitmentTerm.getText().contains("1 Year")
        && estimateTotalCost.getText().contains("USD 1,082.77 per 1 month");
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage clickEmailEstimate() {
    forceClickElementWhenClickable(emailEstimateButton);
    return new GoogleCloudPricingCalculatorEmailEstimatePage(driver);
  }

  private WebElement waitUntilElementIsClickable(WebElement element) {
    return new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(element));
  }

  // only for elements that continuously ignore clicks
  private void forceClickElementWhenClickable (WebElement element) {
    waitUntilElementIsClickable(element);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

}