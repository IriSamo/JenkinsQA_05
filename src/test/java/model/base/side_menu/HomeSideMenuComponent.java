package model.base.side_menu;

import io.qameta.allure.Step;
import model.BuildHistoryPage;
import model.ManageJenkinsPage;
import model.NewItemPage;
import model.PeoplePage;
import model.views.MyViewsPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomeSideMenuComponent extends BaseSideMenuComponent {

    @FindBy(linkText = "New Item")
    private WebElement newItem;

    @FindBy(xpath = "//span/a[@href='/asynchPeople/']")
    private WebElement people;

    @FindBy(linkText = "Build History")
    private WebElement buildHistory;

    @FindBy(linkText = "Manage Jenkins")
    private WebElement manageJenkins;

    @FindBy(css = "a[href='/me/my-views']")
    private WebElement myViews;

    public HomeSideMenuComponent(WebDriver driver) {
        super(driver);
    }

    public NewItemPage<?> clickNewItem() {
        newItem.click();

        return new NewItemPage<>(getDriver(), null);
    }

    public PeoplePage clickPeople() {
        people.click();

        return new PeoplePage(getDriver());
    }

    public BuildHistoryPage clickBuildHistory() {
        buildHistory.click();

        return new BuildHistoryPage(getDriver());
    }

    @Step("Click 'Manage Jenkins' link on side menu")
    public ManageJenkinsPage clickManageJenkins() {
        manageJenkins.click();

        return new ManageJenkinsPage(getDriver());
    }

    public MyViewsPage clickMyViewsSideMenuLink() {
        myViews.click();

        return new MyViewsPage(getDriver());
    }
}
