package restrictions;

import base.TestBase;
import configuration.EnvironmentVariables;
import enums.EnvironmentVariable;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.home.HomePage;
import pages.hooks.TopBar;
import pages.login.LoginPage;
import pages.space.ConfluencePage;
import pages.space.SpacePage;

public class ModifyRestrictionsTest extends TestBase {
  private final String mainUserEmail = EnvironmentVariables.getStringValue(EnvironmentVariable.MAIN_USER_EMAIL);
  private final String mainUserPassword = EnvironmentVariables.getStringValue(EnvironmentVariable.MAIN_USER_PASSWORD);
  private final String secondaryUserEmail =
          EnvironmentVariables.getStringValue(EnvironmentVariable.SECONDARY_USER_EMAIL);
  private final String secondaryUserPassword =
          EnvironmentVariables.getStringValue(EnvironmentVariable.SECONDARY_USER_PASSWORD);
  private final String secondaryUserName = "B J";
  private final String spaceName = "test";
  private final String pageName = "test page";

  @Test
  public void checkVisibleEditableForEveryoneRestriction() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = loginPage.login(mainUserEmail, mainUserPassword);
    homePage.waitForPageToLoad();
    SpacePage spacePage = homePage.openSpace(spaceName);
    ConfluencePage confluencePage = spacePage.openPage(pageName);
    confluencePage.openRestrictions();
    confluencePage.setVisibleEditableRestriction();
    TopBar topBar = new TopBar(driver);
    topBar.logout();
    loginPage.logout();
    loginPage.returnToBaseUrl();
    homePage = loginPage.login(secondaryUserEmail, secondaryUserPassword);
    homePage.waitForPageToLoad();
    spacePage = homePage.openSpace(spaceName);
    Assert.assertTrue(spacePage.isPageVisible(pageName), pageName + " page is visible");
    confluencePage = spacePage.openPage(pageName);
    Assert.assertTrue(confluencePage.isEditPageBtnVisible(), pageName + "page is editable");
  }

  @Test
  public void checkVisibleForEveryoneEditableForChosenRestrictionPositiveScenario() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = loginPage.login(mainUserEmail, mainUserPassword);
    homePage.waitForPageToLoad();
    SpacePage spacePage = homePage.openSpace(spaceName);
    ConfluencePage confluencePage = spacePage.openPage(pageName);
    confluencePage.openRestrictions();
    confluencePage.setVisibleUneditableRestriction(secondaryUserName);
    TopBar topBar = new TopBar(driver);
    topBar.logout();
    loginPage.logout();
    loginPage.returnToBaseUrl();
    homePage = loginPage.login(secondaryUserEmail, secondaryUserPassword);
    homePage.waitForPageToLoad();
    spacePage = homePage.openSpace(spaceName);
    Assert.assertTrue(spacePage.isPageVisible(pageName), pageName + " page is visible");
    confluencePage = spacePage.openPage(pageName);
    Assert.assertTrue(confluencePage.isEditPageBtnVisible(), "Page is editable");
  }

  @Test
  public void checkVisibleForEveryoneEditableForChosenRestrictionNegativeScenario() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = loginPage.login(mainUserEmail, mainUserPassword);
    homePage.waitForPageToLoad();
    SpacePage spacePage = homePage.openSpace(spaceName);
    ConfluencePage confluencePage = spacePage.openPage(pageName);
    confluencePage.openRestrictions();
    confluencePage.setVisibleUneditableRestriction();
    confluencePage.openRestrictions();
    confluencePage.deleteUserFromRestrictionsIfPresent(secondaryUserName);
    TopBar topBar = new TopBar(driver);
    topBar.logout();
    loginPage.logout();
    loginPage.returnToBaseUrl();
    homePage = loginPage.login(secondaryUserEmail, secondaryUserPassword);
    homePage.waitForPageToLoad();
    spacePage = homePage.openSpace(spaceName);
    Assert.assertTrue(spacePage.isPageVisible(pageName), pageName + " page is visible");
    confluencePage = spacePage.openPage(pageName);
    Assert.assertFalse(confluencePage.isEditPageBtnVisible(), "Page isn't editable");
  }

  @Test
  public void checkVisibleEditableForChosenRestrictionPositiveScenario() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = loginPage.login(mainUserEmail, mainUserPassword);
    homePage.waitForPageToLoad();
    SpacePage spacePage = homePage.openSpace(spaceName);
    ConfluencePage confluencePage = spacePage.openPage(pageName);
    confluencePage.openRestrictions();
    confluencePage.setInvisibleUneditableRestriction(secondaryUserName);
    TopBar topBar = new TopBar(driver);
    topBar.logout();
    loginPage.logout();
    loginPage.returnToBaseUrl();
    homePage = loginPage.login(secondaryUserEmail, secondaryUserPassword);
    homePage.waitForPageToLoad();
    spacePage = homePage.openSpace(spaceName);
    Assert.assertTrue(spacePage.isPageVisible(pageName), pageName + " page is visible");
    confluencePage = spacePage.openPage(pageName);
    Assert.assertTrue(confluencePage.isEditPageBtnVisible(), "Page is editable");
  }

  @Test
  public void checkVisibleEditableForChosenRestrictionNegativeScenario() {
    LoginPage loginPage = new LoginPage(driver);
    HomePage homePage = loginPage.login(mainUserEmail, mainUserPassword);
    homePage.waitForPageToLoad();
    SpacePage spacePage = homePage.openSpace(spaceName);
    ConfluencePage confluencePage = spacePage.openPage(pageName);
    confluencePage.openRestrictions();
    confluencePage.setInvisibleUneditableRestriction();
    confluencePage.openRestrictions();
    confluencePage.deleteUserFromRestrictionsIfPresent(secondaryUserName);
    TopBar topBar = new TopBar(driver);
    topBar.logout();
    loginPage.logout();
    loginPage.returnToBaseUrl();
    homePage = loginPage.login(secondaryUserEmail, secondaryUserPassword);
    homePage.waitForPageToLoad();
    spacePage = homePage.openSpace(spaceName);
    Assert.assertFalse(spacePage.isPageVisible(pageName), pageName + " page isn't visible");
  }
}
