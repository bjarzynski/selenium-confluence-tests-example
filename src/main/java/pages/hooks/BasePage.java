package pages.hooks;


import configuration.EnvironmentVariables;
import enums.EnvironmentVariable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
  protected WebDriver driver;
  private final long timeout = EnvironmentVariables.getLongValue(EnvironmentVariable.TIMEOUT);

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public void waitForElementAttribute(WebElement element, String attributeName, String attributeValue) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(ExpectedConditions.attributeContains(element, attributeName, attributeValue));
  }

  public void waitForElementToBeClickable(WebElement element) {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(ExpectedConditions.elementToBeClickable(element));
  }

  public void waitForDialogToDisappear() {
    new WebDriverWait(driver, Duration.ofSeconds(timeout))
            .until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("section[role=dialog]"))));
  }

  public void returnToBaseUrl() {
    driver.get(EnvironmentVariables.getStringValue(EnvironmentVariable.BASE_URL));
  }

}