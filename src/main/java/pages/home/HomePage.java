package pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pages.hooks.BasePage;
import pages.space.SpacePage;

public class HomePage extends BasePage {
  @FindBy(id = "com-atlassian-confluence")
  private WebElement homePageBody;

  public HomePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public void waitForPageToLoad() {
    this.waitForElementAttribute(homePageBody, "data-aui-version", "SNAPSHOT");
  }

  public SpacePage openSpace(String spaceName) {
    driver.findElement(By.xpath("//div[@data-testid='spaces']//div[text()='" + spaceName + "']")).click();
    return PageFactory.initElements(driver, SpacePage.class);
  }
}
