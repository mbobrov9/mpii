package com.projekt.pluk;

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
public class LoginTest {
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
    public void login() {
        driver.get("http://localhost:8080/login?logout");
        driver.manage().window().setSize(new Dimension(1936, 1056));
        driver.findElement(By.linkText("Законы")).click();
        driver.findElement(By.id("username")).sendKeys("123");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("123");
        driver.findElement(By.cssSelector(".btn")).click();

    }
}