package webdriver.page;

import static webdriver.base.PageHelpers.forceClickWhenClickable;
import static webdriver.base.PageHelpers.waitUntilClickable;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {

  @FindBy(
      xpath = "//md-pagination-wrapper/descendant::div[@title='Compute Engine' and @class='tab-holder compute']")
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
      xpath = "//form[@name='ComputeEngineForm']/descendant::*[contains(text(), 'Add to Estimate')]")
  private WebElement addToEstimateButton;

  @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'VM class:')]")
  private WebElement estimateVmClass;

  @FindBy(
      xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Instance type:')]")
  private WebElement estimateInstanceType;

  @FindBy(xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Region:')]")
  private WebElement estimateRegion;

  @FindBy(
      xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Total available local SSD space')]")
  private WebElement estimateLocalSsdSpace;

  @FindBy(
      xpath = "//div[@class='md-list-item-text ng-binding' and contains(text(), 'Commitment term:')]")
  private WebElement estimateCommitmentTerm;

  @FindBy(xpath = "//b[@class='ng-binding' and contains(text(), 'Total Estimated Cost')]")
  private WebElement estimateTotalCost;

  @FindBy(xpath = "//button[@id='email_quote']")
  private WebElement emailEstimateButton;

  public GoogleCloudPricingCalculatorPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public GoogleCloudPricingCalculatorPage clickComputeEngineButton() {
    waitUntilClickable(driver, computeEngineButton).click();
    return this;
  }

  public GoogleCloudPricingCalculatorPage inputNumberOfInstances(Integer numberOfInstances) {
    waitUntilClickable(driver, numberOfInstancesField).sendKeys(numberOfInstances.toString());
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickFreeOperatingSystem() {
    forceClickWhenClickable(driver, operatingSystemField);
    forceClickWhenClickable(driver, operatingSystemFreeSoftwareOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickRegularMachineClass() {
    forceClickWhenClickable(driver, machineClassField);
    forceClickWhenClickable(driver, machineClassRegularOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickStandardEightMachineType() {
    forceClickWhenClickable(driver, machineTypeField);
    forceClickWhenClickable(driver, machineTypeStandardEightOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage addGpus() {
    forceClickWhenClickable(driver, addGpusCheckbox);
    forceClickWhenClickable(driver, numberOfGpusField);
    forceClickWhenClickable(driver, numberOfGpusSingleGpuOption);
    forceClickWhenClickable(driver, gpuTypeField);
    forceClickWhenClickable(driver, gpuTypeChooseGpuNVIDIATeslaV100Type);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickLocalSsd() {
    forceClickWhenClickable(driver, localSsdField);
    forceClickWhenClickable(driver, localSsd2x375Option);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickDatacenterLocation() {
    forceClickWhenClickable(driver, datacenterLocationField);
    forceClickWhenClickable(driver, datacenterLocationFrankfurtOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage pickCommittedUsage() {
    forceClickWhenClickable(driver, committedUsageField);
    forceClickWhenClickable(driver, committedUsageOneYearOption);
    return this;
  }

  public GoogleCloudPricingCalculatorPage addToEstimate() {
    forceClickWhenClickable(driver, addToEstimateButton);
    return this;
  }

  public Map<String, String> checkEstimateFields() {
    new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
        .until(ExpectedConditions.visibilityOfAllElements(
            estimateVmClass,
            estimateInstanceType,
            estimateRegion,
            estimateLocalSsdSpace,
            estimateCommitmentTerm,
            estimateTotalCost));
    Map<String, String> map = new HashMap<>();
    map.put("VM Class", estimateVmClass.getText());
    map.put("Instance Type", estimateInstanceType.getText());
    map.put("Region", estimateRegion.getText());
    map.put("Local SSD", estimateLocalSsdSpace.getText());
    map.put("Commitment", estimateCommitmentTerm.getText());
    map.put("Total Cost", estimateTotalCost.getText());
    return map;
  }

  public GoogleCloudPricingCalculatorEmailEstimatePage clickEmailEstimate() {
    forceClickWhenClickable(driver, emailEstimateButton);
    return new GoogleCloudPricingCalculatorEmailEstimatePage(driver);
  }
}
