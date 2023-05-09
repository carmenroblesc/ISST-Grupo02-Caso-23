package es.upm.dit.isst.medconweb;
// Generated by Selenium IDE
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;
public class MedicomicroserviciosTest {
  private WebDriver driver;
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    System.setProperty( "webdriver.chrome.driver", "/home/isst/chromedriver");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  public String waitForWindow(int timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    Set<String> whNow = driver.getWindowHandles();
    Set<String> whThen = (Set<String>) vars.get("window_handles");
    if (whNow.size() > whThen.size()) {
      whNow.removeAll(whThen);
    }
    return whNow.iterator().next();
  }
  @Test
  public void medicomicroservicios() {
    driver.get("http://localhost:8080/medicos");
    driver.manage().window().setSize(new Dimension(899, 1032));
    driver.findElement(By.id("ncolegiado")).sendKeys("23456");
    driver.findElement(By.id("password")).sendKeys("ana1");
    driver.findElement(By.cssSelector(".button")).click();
    vars.put("window_handles", driver.getWindowHandles());
    driver.findElement(By.linkText("Historial")).click();
    vars.put("win7366", waitForWindow(2000));
    vars.put("root", driver.getWindowHandle());
    driver.switchTo().window(vars.get("win7366").toString());
    driver.switchTo().window(vars.get("root").toString());
    vars.put("window_handles", driver.getWindowHandles());
    driver.findElement(By.linkText("Recetas")).click();
    vars.put("win9424", waitForWindow(2000));
    driver.switchTo().window(vars.get("win9424").toString());
    driver.switchTo().window(vars.get("root").toString());
    driver.findElement(By.linkText("Solicitar prueba")).click();
    driver.switchTo().window(vars.get("win9424").toString());
    driver.switchTo().window(vars.get("root").toString());
    driver.findElement(By.cssSelector("tr:nth-child(1) .btn:nth-child(10)")).click();
    driver.switchTo().window(vars.get("win9424").toString());
    driver.switchTo().window(vars.get("root").toString());
    driver.findElement(By.linkText("FINALIZAR")).click();
  }
}