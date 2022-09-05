package pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.home.HomePage;
import pages.hooks.BasePage;

public class LoginPage extends BasePage {
  @FindBy(id = "username")
  private WebElement usernameField;
  @FindBy(id = "login-submit")
  private WebElement loginSubmitBtn;
  @FindBy(id = "password")
  private WebElement passwordField;
  @FindBy(id = "logout-submit")
  private WebElement logoutBtn;

  public LoginPage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public HomePage login(String username, String password) {
    usernameField.sendKeys(username);
    loginSubmitBtn.click();
    waitForElementToBeClickable(passwordField);
    passwordField.sendKeys(password);
    loginSubmitBtn.click();
    return PageFactory.initElements(driver, HomePage.class);
  }

  public void logout() {
    logoutBtn.click();
  }
}
