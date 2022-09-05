package pages.space;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.hooks.BasePage;

public class SpacePage extends BasePage {
  public SpacePage(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  public ConfluencePage openPage(String pageName) {
    driver.findElement(By.xpath("//div[@data-testid='pageTree']//span[text()='" + pageName + "']")).click();
    return PageFactory.initElements(driver, ConfluencePage.class);
  }

  public boolean isPageVisible(String pageName) {
    try {
      driver.findElement(By.xpath("//div[@data-testid='pageTree']//span[text()='" + pageName + "']"));
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }
}
