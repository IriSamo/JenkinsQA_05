package tests;

import model.HomePage;
import model.folder.FolderStatusPage;
import model.organization_folder.OrgFolderConfigPage;
import model.organization_folder.OrgFolderStatusPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import runner.BaseTest;
import runner.TestUtils;

import java.util.List;

public class OrganizationFolderTest extends BaseTest {
    private static final String NAME_ORG_FOLDER = TestUtils.getRandomStr();
    private static final String NAME_FOLDER = TestUtils.getRandomStr();
    private static final String DISPLAY_NAME = TestUtils.getRandomStr();

    @Test
    public void testCreateOrganizationFolder() {
        String actualOrgFolderDisplayName = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectOrgFolderAndClickOk()
                .clickSaveButton()
                .getNameText();

        Assert.assertEquals(actualOrgFolderDisplayName, NAME_ORG_FOLDER);
    }

    @Test
    public void testRenameOrganizationFolder() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectOrgFolderAndClickOk()
                .clickSaveButton()
                .getSideMenu()
                .clickRenameSideMenu()
                .clearFieldAndInputNewName("New name " + NAME_ORG_FOLDER)
                .clickRenameButton()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains("New name " + NAME_ORG_FOLDER));
    }

    @Test
    public void testCreateOrgFolder() {
        List<String> allFolders = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectFolderAndClickOk()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard()
                .getJobNamesList();

        Assert.assertTrue(allFolders.contains(NAME_ORG_FOLDER));
    }

    @Test
    public void testOrgFolderEmptyNameErr() {
        String errMessageEmptyName = new HomePage(getDriver())
                .clickNewItem()
                .setItemName("")
                .selectOrgFolderAndClickOk()
                .getErrorMessageEmptyField();

        Assert.assertEquals(errMessageEmptyName,
                "» This field cannot be empty, please enter a valid name");
    }

    @Test
    public void testCreateOrgFolderWithEmptyName() {
        OrgFolderConfigPage orgFolderConfigPage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName("")
                .selectOrgFolderAndClickOk();

        Assert.assertFalse(orgFolderConfigPage.isOkButtonEnabled());
    }

    @Test(dependsOnMethods = "testCreateOrgFolder")
    public void testCreateOrgFolderExistName() {
        String errMessage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectExistFolderAndClickOk()
                .getErrorMessage();

        Assert.assertEquals(errMessage, "A job already exists with the name ‘"
                + NAME_ORG_FOLDER + "’");
    }

    @Test(dependsOnMethods = "testConfigureOrganizationFolder")
    public void testDeleteOrganizationFolderDependsMethods() {
        HomePage homePage = new HomePage(getDriver())
                .clickOrgFolder(DISPLAY_NAME)
                .getSideMenu()
                .clickDeleteOrganizationFolder()
                .clickSaveButton();

        Assert.assertFalse(homePage.getJobNamesList().contains(DISPLAY_NAME));
    }

    @Test(dependsOnMethods = "testCreateOrganizFolder")
    public void testConfigureOrganizationFolder() {
        final String description = TestUtils.getRandomStr();

        OrgFolderStatusPage orgFolderStatusPage = new HomePage(getDriver())
                .clickOrgFolder(NAME_ORG_FOLDER)
                .getSideMenu()
                .clickConfigureSideMenu()
                .inputDisplayName(DISPLAY_NAME)
                .inputDescription(description)
                .clickSaveButton();

        Assert.assertEquals(orgFolderStatusPage.getNameText(), DISPLAY_NAME);
        Assert.assertEquals(orgFolderStatusPage.getAdditionalDescriptionText(), description);

        HomePage homePage = orgFolderStatusPage.getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains(DISPLAY_NAME));
    }

    @Test
    public void testCreateOrganizFolder() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectOrgFolderAndClickOk()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains(NAME_ORG_FOLDER));
    }

    @Test
    public void testOrgFolderCreate() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_ORG_FOLDER)
                .selectOrgFolderAndClickOk()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains(NAME_ORG_FOLDER));
    }

    @Test
    public void testFolderCreate() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .setItemName(NAME_FOLDER)
                .selectFolderAndClickOk()
                .clickSaveButton()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains(NAME_FOLDER));
    }

    @Test(dependsOnMethods = {"testFolderCreate", "testOrgFolderCreate"})
    public void testMoveOrgFolderToFolder() {
        FolderStatusPage folderStatusPage = new HomePage(getDriver())
                .clickOrgFolder(NAME_ORG_FOLDER)
                .clickMoveButton()
                .selectFolder(NAME_FOLDER)
                .clickMove()
                .getBreadcrumbs()
                .clickDashboard()
                .clickFolder(NAME_FOLDER);

        Assert.assertTrue(folderStatusPage.getJobList().contains(NAME_ORG_FOLDER));
    }

    @Test(dependsOnMethods = "testMoveOrgFolderToFolder")
    public void testMoveOrgFolderToDashboard() {
        HomePage homePage = new HomePage(getDriver())
                .clickFolder(NAME_FOLDER)
                .clickOrgFolder(NAME_ORG_FOLDER)
                .clickMoveButton()
                .selectOptionToDashBoard()
                .clickMove()
                .getBreadcrumbs()
                .clickDashboard();

        Assert.assertTrue(homePage.getJobNamesList().contains(NAME_ORG_FOLDER));
    }
}
