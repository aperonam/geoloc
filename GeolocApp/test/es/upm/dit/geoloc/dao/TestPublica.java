package es.upm.dit.geoloc.dao;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestPublica {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
	  System.setProperty( "webdriver.chrome.driver", "/home/isst/chromedriver");
	  driver = new ChromeDriver();

    baseUrl = "https://www.katalon.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testPublica() throws Exception {
    driver.get("http://localhost:8080/GeolocApp/Index.jsp");
    driver.findElement(By.id("publica")).click();
    driver.findElement(By.id("text-area")).click();
    driver.findElement(By.id("text-area")).clear();
    driver.findElement(By.id("text-area")).sendKeys("Test comentario de prueba");
    driver.findElement(By.xpath("//div[@id='cuerpoPost']/main/div")).click();
    // ERROR: Caught exception [unknown command [editContent]]
    driver.findElement(By.xpath("//div[@id='cuerpoPost']/button/p")).click();
    driver.findElement(By.xpath("//div[@id='map']/div/div/div/div[3]/div[2]/div[3]/div[3]/img")).click();
    assertEquals("Test comentario de prueba", driver.findElement(By.xpath("//div[@id='iw-container']/div[2]/p")).getText());
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
