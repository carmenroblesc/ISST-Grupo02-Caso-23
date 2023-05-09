package es.upm.dit.isst.medconweb;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.*;
public class MedicologinlogoutTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
    // para ejecutarlo se debe descargar el chrome driver y aañdirlo a la ruta
    System.setProperty( "webdriver.chrome.driver", "/home/isst/chromedriver");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void medicologinlogout() {
    driver.get("http://localhost:8080/medicos");
    driver.findElement(By.id("ncolegiado")).click();
    driver.findElement(By.id("ncolegiado")).sendKeys("12345");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("laura1");
    driver.findElement(By.cssSelector(".button")).click();
    driver.findElement(By.xpath("//a[contains(@href, \'/medicos\')]")).click();
    driver.findElement(By.id("ncolegiado")).click();
    driver.findElement(By.id("ncolegiado")).sendKeys("23456");
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("ana1");
    driver.findElement(By.cssSelector(".button")).click();
    driver.findElement(By.cssSelector("tr:nth-child(3) .btn-success")).click();
    driver.findElement(By.xpath("//a[contains(@href, \'/medicos\')]")).click();
  }
}
