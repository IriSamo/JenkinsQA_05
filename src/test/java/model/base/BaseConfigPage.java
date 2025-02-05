package model.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public abstract class BaseConfigPage<StatusPage extends BaseStatusPage<?, ?>, Self extends BaseConfigPage<?, ?>> extends MainBasePage {

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement saveButton;

    @FindBy(name = "Apply")
    private WebElement applyButton;

    @FindBy(css = "button.task-link")
    private List<WebElement> configSideMenu;

    public BaseConfigPage(WebDriver driver) {
        super(driver);
    }

    protected abstract StatusPage createStatusPage();

    public StatusPage clickSaveButton() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(saveButton)).click();

        return createStatusPage();
    }

    public Self clickApplyButton() {
        getWait(5).until(ExpectedConditions.elementToBeClickable(applyButton)).click();

        return (Self) this;
    }

    public Set<String> collectConfigSideMenu() {
        return configSideMenu.stream().map(WebElement::getText).collect(Collectors.toCollection(TreeSet::new));
    }
}
