package pages.hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TopBar extends BasePage {

  @FindBy(css = "span[data-testid=app-navigation-profile] button")
  private WebElement profileBtn;

  @FindBy(css = "div[data-testid=app-navigation-profile-menu] > div:nth-child(3) > a")
  private WebElement logoutOption;

  public TopBar(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void logout() {
    waitForElementToBeClickable(profileBtn);
    profileBtn.click();
    logoutOption.click();
  }
}
