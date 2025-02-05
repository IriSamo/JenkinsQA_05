package model.config_pages;

import model.base.BaseConfigPage;
import model.status_pages.FreestyleProjectStatusPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import runner.TestUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static runner.TestUtils.scrollToElement_PlaceInCenter;

public class FreestyleProjectConfigPage extends BaseConfigPage<FreestyleProjectStatusPage, FreestyleProjectConfigPage> {

    @FindBy(tagName = "h1")
    private WebElement headline;

    @FindBy(css = "#main-panel > p")
    private WebElement errorMsg;

    @FindBy(xpath = "//span/label[text()='Discard old builds']")
    private WebElement discardOldBuildsCheckbox;

    @FindBy(xpath = "//input[@name='_.daysToKeepStr']")
    private WebElement daysToKeepBuilds;

    @FindBy(xpath = "//input[@name='_.numToKeepStr']")
    private WebElement maxNumberOfBuildsToKeep;

    @FindBy(xpath = "//label[text() = 'This project is parameterized']")
    private WebElement checkBoxProjectIsParametrized;

    @FindBy(xpath = "//button[text() = 'Add Parameter']")
    private WebElement buttonAddParameter;

    @FindBy(xpath = "//a[text() = 'String Parameter']")
    private WebElement stringParameter;

    @FindBy(xpath = "//input[@name = 'parameter.name']")
    private WebElement fieldInputStringParameterName;

    @FindBy(xpath = "//input[@name = 'parameter.defaultValue']")
    private WebElement fieldInputStringParameterDefaultValue;

    @FindBy(xpath = "//a[text() = 'Choice Parameter']")
    private WebElement choiceParameter;

    @FindBy(xpath = "//div[@class = 'jenkins-form-item hetero-list-container with-drag-drop  ']/div[2]//input[@name = 'parameter.name']")
    private WebElement fieldInputChoiceParameterName;

    @FindBy(xpath = "//textarea[@name= 'parameter.choices']")
    private WebElement fieldInputChoiceParameterValue;

    @FindBy(xpath = "//a[text() = 'Boolean Parameter']")
    private WebElement booleanParameter;

    @FindBy(xpath = "//div[@class = 'jenkins-form-item hetero-list-container with-drag-drop  ']/div[3]//input[@name = 'parameter.name']")
    private WebElement fieldInputBooleanParameterName;

    @FindBy(xpath = "//label[text() = 'Set by Default']")
    private WebElement setByDefault;

    @FindBy(xpath = "//label[text() = 'Git']")
    private WebElement radioGitButton;

    @FindBy(xpath = "//div[text() = 'Repository URL']/following-sibling::div/input")
    private WebElement fieldInputRepositoryURL;

    @FindBy(xpath = "//button[text()='Add build step']")
    private WebElement buildStepsButton;

    @FindBy(xpath = "//button[text()='Add build step']/../../..//a[@href='#']")
    private List<WebElement> listOfElementsInBuildStepsDropDown;

    @FindBy(xpath = "//button[@data-section-id='build-triggers']")
    private WebElement buildTriggersSideMenuOption;

    @FindBy(xpath = "//label[text()='Build periodically']")
    private WebElement buildPeriodicallyOption;

    @FindBy(name = "hudson-triggers-TimerTrigger")
    private WebElement buildPeriodicallyCheckbox;

    @FindBy(xpath = "//div[contains(text(), 'Branch Specifier')]/following-sibling::div/input")
    private WebElement branchSpecifierInputField;

    @FindBy(xpath = "//button[@data-section-id='source-code-management']")
    private WebElement linkSourceCodeManagement;

    @Override
    protected FreestyleProjectStatusPage createStatusPage() {
        return new FreestyleProjectStatusPage(getDriver());
    }

    public FreestyleProjectConfigPage(WebDriver driver) {
        super(driver);
    }

    public FreestyleProjectConfigPage clickDiscardOldBuildsCheckbox() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(discardOldBuildsCheckbox)).click();

        return this;
    }

    public FreestyleProjectConfigPage typeDaysToKeepBuilds(String numberOfDays) {
        getWait(5).until(ExpectedConditions.elementToBeClickable(daysToKeepBuilds)).sendKeys(numberOfDays);

        return this;
    }

    public String getNumberOfDaysToKeepBuilds() {

        return daysToKeepBuilds.getAttribute("value");
    }

    public FreestyleProjectConfigPage typeMaxNumberOfBuildsToKeep(String numberOfBuilds) {
        maxNumberOfBuildsToKeep.sendKeys(numberOfBuilds);

        return this;
    }

    public String getMaxNumberOfBuildsToKeep() {

        return maxNumberOfBuildsToKeep.getAttribute("value");
    }

    public FreestyleProjectConfigPage switchONCheckBoxThisProjectIsParametrized() {
        checkBoxProjectIsParametrized.click();

        return this;
    }

    public FreestyleProjectConfigPage clickButtonAddParameter() {
        buttonAddParameter.click();

        return this;
    }

    public FreestyleProjectConfigPage selectStringParameter() {
        stringParameter.click();

        return this;
    }

    public FreestyleProjectConfigPage inputStringParameterName(String stringParameterName) {
        getWait(5).until(ExpectedConditions.visibilityOf(fieldInputStringParameterName)).sendKeys(stringParameterName);

        return this;
    }

    public FreestyleProjectConfigPage inputStringParameterDefaultValue(String stringParameterDefaultValue) {
        fieldInputStringParameterDefaultValue.sendKeys(stringParameterDefaultValue);

        return this;
    }

    public FreestyleProjectConfigPage selectChoiceParameter() {
        choiceParameter.click();

        return this;
    }

    public FreestyleProjectConfigPage inputChoiceParameterName(String choiceParameterName) {
        getWait(5).until(ExpectedConditions.visibilityOf(fieldInputChoiceParameterName)).sendKeys(choiceParameterName);

        return this;
    }

    public FreestyleProjectConfigPage inputChoiceParameterValue(String choiceParameterValue) {
        fieldInputChoiceParameterValue.sendKeys(choiceParameterValue);

        return this;
    }

    public FreestyleProjectConfigPage selectBooleanParameter() {
        booleanParameter.click();

        return this;
    }

    public FreestyleProjectConfigPage inputBooleanParameterName(String booleanParameterName) {
        getWait(10).until(ExpectedConditions.visibilityOf(fieldInputBooleanParameterName)).sendKeys(booleanParameterName);

        return this;
    }

    public FreestyleProjectConfigPage switchONBooleanParameterAsDefault() {
        setByDefault.click();

        return this;
    }

    public FreestyleProjectConfigPage scrollAndClickAddParameterButton() {
        scrollToElement_PlaceInCenter(getDriver(), buttonAddParameter);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(buttonAddParameter)).click();

        return this;
    }

    public FreestyleProjectConfigPage selectSourceCodeManagementGIT() {
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(radioGitButton));
        getAction()
                .scrollToElement(radioGitButton)
                .moveToElement(radioGitButton)
                .click()
                .perform();

        return this;
    }

    public FreestyleProjectConfigPage inputGITRepositoryURL(String url) {
        getWait(10).until(ExpectedConditions.elementToBeClickable(fieldInputRepositoryURL)).sendKeys(url);

        return this;
    }

    public FreestyleProjectConfigPage switchOFFCheckBoxThisProjectIsParametrized() {
        checkBoxProjectIsParametrized.click();

        return this;
    }

    public FreestyleProjectConfigPage openAddBuildStepDropDown() {
        scrollToElement_PlaceInCenter(getDriver(), buildStepsButton);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(buildStepsButton));
        getWait(5).until(ExpectedConditions.elementToBeClickable(buildStepsButton));
        buildStepsButton.click();

        return this;
    }

    public Set<String> getOptionsInBuildStepsDropDown() {

        return listOfElementsInBuildStepsDropDown
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toCollection(HashSet::new));
    }

    public FreestyleProjectConfigPage clickBuildTriggersSideMenuOption() {
        buildTriggersSideMenuOption.click();

        return this;
    }

    public FreestyleProjectConfigPage scrollAndClickBuildPeriodicallyCheckbox() {
        scrollToElement_PlaceInCenter(getDriver(), buildPeriodicallyOption);
        getWait(20).until(TestUtils.ExpectedConditions.elementIsNotMoving(buildPeriodicallyOption)).click();
        getWait(10).until(ExpectedConditions
                .elementSelectionStateToBe(By.name("hudson-triggers-TimerTrigger"), true));

        return this;
    }

    public boolean verifyThatBuildPeriodicallyCheckboxIsSelected() {

        return buildPeriodicallyCheckbox.isSelected();
    }

    public FreestyleProjectConfigPage inputBranchSpecifier(String branchSpecifier) {
        scrollToElement_PlaceInCenter(getDriver(), branchSpecifierInputField);
        getWait(5).until(TestUtils.ExpectedConditions.elementIsNotMoving(branchSpecifierInputField)).clear();
        branchSpecifierInputField.sendKeys(branchSpecifier);

        return this;
    }

    public FreestyleProjectConfigPage clickLinkSourceCodeManagement() {
        linkSourceCodeManagement.click();

        return this;
    }

    public String getHeadlineText() {
        return headline.getText();
    }
}