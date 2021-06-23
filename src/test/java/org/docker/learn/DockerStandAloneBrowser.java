package org.docker.learn;

import static org.testng.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DockerStandAloneBrowser {
	static WebDriver driver = null;
	@Test
	public static void localrun() {
		WebDriverManager.chromedriver().setup();
		driver =  (WebDriver) new ChromeDriver();
		String baseUrl = "https://evisa.rop.gov.om/en/evisalogin";
		driver.get(baseUrl);
		String actualTitle= driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "evisalogin - Evisa";
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test passed");
		} else {
			System.out.println("Test Failed");
		}
		System.out.println("Execution completed hence closing the driver now");
		driver.quit();
	}
	@Test
	public static void dockerrunchrome() throws MalformedURLException {
		System.out.println("Starting the test now in chrome");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.CHROME);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		String baseUrl = "https://evisa.rop.gov.om/en/evisalogin";
		System.out.println("Navigating to URL in chrome");
		driver.get(baseUrl);
		String actualTitle= driver.getTitle();
		System.out.println(actualTitle);
		String expectedTitle = "evisalogin - Evisa";
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test passed in chrome");
		} else {
			System.out.println("Test Failed in chrome");
		}
		driver.quit();
	}
	@Test(enabled=false)
	public static void dockerrunfirefox() throws MalformedURLException {
		System.out.println("Starting the test now in firefox");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.FIREFOX);
		FirefoxOptions options = new FirefoxOptions();
		options.addArguments("--no-sandbox");
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		driver.manage().window().maximize();
		driver.navigate().to("https://evisa.rop.gov.om/en/evisalogin");;
		System.out.println("Navigating to URL in firefox");
		String actualTitle= driver.getTitle();
		System.out.println("Title is: "+actualTitle);
		String expectedTitle = "evisalogin - Evisa";
		if (actualTitle.contentEquals(expectedTitle)) {
			System.out.println("Test passed in firefox");
		} else {
			System.out.println("Test Failed in firefox");
		}
		driver.quit();
	}

	@Test(enabled=false)
	public static void runInSelenoid() throws MalformedURLException {
		System.out.println("Starting the test in selenoid");
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName(BrowserType.CHROME);
		cap.setCapability("enableVNC", true);
		cap.setCapability("enableVideo", false);
		cap.setCapability("videoName", "video_1");		
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println(driver.getTitle());
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}

	@Test(enabled=false)
	public static void runlocal() throws MalformedURLException {
		System.out.println("Starting the test in selenoid");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println(driver.getTitle());
		
		driver.findElement(By.xpath("//*[@id='divUsername']/span")).click();
		WebElement element =driver.findElement(By.xpath("//*[@id='divUsername']/span"));
		driver.findElements(By.id("divUsername")).get(1);
		element.sendKeys("Admin");
		//System.out.println("Value is:"+value);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.quit();
	}
}
