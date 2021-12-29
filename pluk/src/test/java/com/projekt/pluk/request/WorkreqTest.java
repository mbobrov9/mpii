package com.projekt.pluk.request; // Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
public class WorkreqTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty("webdriver.edge.driver", "C:\\Users\\mbobr\\Downloads\\edgedriver_win64\\msedgedriver.exe");
    driver = new EdgeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void workreq() {
    driver.get("http://localhost:8080/login");
    driver.manage().window().setSize(new Dimension(1920, 1040));
    driver.findElement(By.id("username")).sendKeys("ecilop");
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).click();
    {
      WebElement element = driver.findElement(By.id("username"));
      Actions builder = new Actions(driver);
      builder.doubleClick(element).perform();
    }
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("ecilop");
    driver.findElement(By.cssSelector(".btn")).click();
    driver.get("http://localhost:8080/");
    driver.findElement(By.linkText("Заявления")).click();
    WebElement march8Link =
            driver.findElements(By.id("but1")).get(0);
    march8Link.click();
    driver.findElement(By.name("type")).click();
    {
      WebElement dropdown = driver.findElement(By.name("type"));
      dropdown.findElement(By.xpath("//option[. = 'Цик с гвоздями']")).click();
    }
   // driver.findElement(By.cssSelector("form:nth-child(3) > .btn")).click();
   // driver.findElement(By.cssSelector("form:nth-child(6) > .btn")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(3)")).click();
    driver.findElement(By.linkText("Главная")).click();
    driver.findElement(By.cssSelector("input")).click();
  }
}