package pages.space;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.hooks.BasePage;

public class ConfluencePage extends BasePage {

  @FindBy(id = "editPageLink")
  private WebElement editPageBtn;

  @FindBy(css = "button[data-id=restrictions-button]")
  private WebElement restrictionsBtn;

  @FindBy(css = "div[data-test-id='restrictions-dialog.content-mode-select']")
  private WebElement restrictionsField;

  @FindBy(css = "div[data-test-id='restrictions-dialog.content-mode-select'] div[class*=option]:nth-child(1)")
  private WebElement visibleEditableOption;

  @FindBy(css = "div[data-test-id='restrictions-dialog.content-mode-select'] div[class*=option]:nth-child(2)")
  private WebElement visibleUneditableOption;

  @FindBy(css = "div[data-test-id='restrictions-dialog.content-mode-select'] div[class*=option]:nth-child(3)")
  private WebElement invisibleUneditableOption;

  @FindBy(css = "div[data-test-id=restrictions-dialog-footer] > div > div > div >div:nth-child(2) > button")
  private WebElement applyBtn;

  @FindBy(css = "div[data-test-id=restrictions-dialog-footer] > div > div > div >div:nth-child(1) > button")
  private WebElement cancelBtn;

  @FindBy(css = "div[data-test-id=user-and-group-search]")
  private WebElement userSearchField;

  @FindBy(css = "div[data-test-id=user-and-group-search] input")
  private WebElement userSearchInput;

  @FindBy(css = "div[data-test-id='restrictions-dialog.users-and-groups-search'] button")
  private WebElement addUserBtn;

  public ConfluencePage(WebDriver driver) {
    super(driver);
  }

  public boolean isEditPageBtnVisible() {
    try {
      return editPageBtn.isDisplayed();
    } catch(NoSuchElementException e) {
      return false;
    }
  }

  public void openRestrictions() {
    restrictionsBtn.click();
  }

  public void setVisibleEditableRestriction() {
    restrictionsField.click();
    visibleEditableOption.click();
    applyBtn.click();
    waitForDialogToDisappear();
  }

  public void setVisibleUneditableRestriction(String userName) {
    restrictionsField.click();
    visibleUneditableOption.click();
    addUserToRestrictions(userName);
    applyBtn.click();
    waitForDialogToDisappear();
  }

  public void setVisibleUneditableRestriction() {
    restrictionsField.click();
    visibleUneditableOption.click();
    applyBtn.click();
    waitForDialogToDisappear();
  }

  public void setInvisibleUneditableRestriction(String userName) {
    restrictionsField.click();
    invisibleUneditableOption.click();
    addUserToRestrictions(userName);
    applyBtn.click();
    waitForDialogToDisappear();
  }

  public void setInvisibleUneditableRestriction() {
    restrictionsField.click();
    invisibleUneditableOption.click();
    applyBtn.click();
    waitForDialogToDisappear();
  }

  public void deleteUserFromRestrictionsIfPresent(String userName) {
    if(!driver.findElements(By.xpath("//span[text()='" + userName + "']")).isEmpty()) {
      driver.findElement(By.xpath("//span[text()='" + userName + "']/ancestor::tr//button")).click();
      applyBtn.click();
    } else {
      cancelBtn.click();
    }
    waitForDialogToDisappear();
  }

  private void addUserToRestrictions(String userName) {
    userSearchField.click();
    userSearchInput.sendKeys(userName);
    driver.findElement(
            By.xpath("//div[contains(@class, 'fabric-user-picker__option')]//span[text()='" + userName + "']")).click();
    addUserBtn.click();
  }
}
