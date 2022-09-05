package base;

import configuration.EnvironmentVariables;
import enums.EnvironmentVariable;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.browsers.Browser;
import utils.browsers.DriverFactory;

import java.time.Duration;

public class TestBase {
  protected WebDriver driver;

  @BeforeMethod
  public void beforeTestSetup() {
    setUpDriver();
    String baseUrl = EnvironmentVariables.getStringValue(EnvironmentVariable.BASE_URL);
    driver.get(baseUrl);
    driver.manage().window().maximize();
    long TEN_THOUSANDS_MILLIS = 10000;
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(TEN_THOUSANDS_MILLIS));
  }

  @AfterMethod
  public void afterTestTeardown() {
    driver.close();
  }

  private void setUpDriver() {
    String browserName = EnvironmentVariables.getStringValue(EnvironmentVariable.BROWSER);
    switch (Browser.fromString(browserName)) {
      case CHROME -> driver = DriverFactory.createChromeDriver();
      case EDGE -> driver = DriverFactory.createEdgeDriver();
      case FIREFOX -> driver = DriverFactory.createFirefoxDriver();
      default -> throw new UnsupportedOperationException();
    }
  }
}
