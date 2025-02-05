package tests;

import model.HomePage;
import model.BuildHistoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.ProjectMethodsUtils;
import runner.TestDataUtils;

public class BuildHistoryTest extends BaseTest {

    @Test
    public void testH1HeaderBuildHistory() {
        ProjectMethodsUtils.createNewFreestyleProject(getDriver(), TestDataUtils.FREESTYLE_PROJECT_NAME);

        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertEquals(buildHistoryPage.getHeaderText(), "Build History of Jenkins");
    }

    @Test
    public void testSMLIconsExist() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertTrue(buildHistoryPage.smallSizeIconIsDisplayed());
        Assert.assertTrue(buildHistoryPage.middleSizeIconIsDisplayed());
        Assert.assertTrue(buildHistoryPage.largeSizeIconIsDisplayed());
    }

    @Test
    public void testRssItemsExist() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertTrue(buildHistoryPage.iconAtomFeedForAllIsDisplayed());
        Assert.assertTrue(buildHistoryPage.iconAtomFeedForFailuresIsDisplayed());
        Assert.assertTrue(buildHistoryPage.iconAtomFeedForFoJustLatestBuildsIsDisplayed());
    }

    @Test
    public void testIconLegendExist() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertTrue(buildHistoryPage.isIconDisplayed());
    }

    @Test
    public void testNumberOfColumnsInProjectStatusTable() {
        BuildHistoryPage buildHistoryPage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory();

        Assert.assertEquals(buildHistoryPage.getSize(), 5);
    }

    @Test
    public void testRedirectToMainPage() {
        HomePage homePage = new HomePage(getDriver())
                .getSideMenu()
                .clickBuildHistory()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertEquals(homePage.getHeaderText(), "Welcome to Jenkins!");
    }
}