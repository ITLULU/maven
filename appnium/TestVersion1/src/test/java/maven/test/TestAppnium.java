package maven.test;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import maven.Appnium.AppniumUtils;

public class TestAppnium {

	Logger log=Logger.getLogger(TestAppnium.class);
	 AndroidDriver<WebElement>driver;
	@BeforeTest
	public void beforeTest() {
		try {
			driver=AppniumUtils.Init();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	@Test
//	public void pageOpen()  {
//		log.info("----≤‚ ‘app----");
//		AppniumUtils.TestPageOpen();
//		Reporter.log("pageopen≤‚ ‘ÕÍ≥…");
//	}

	@AfterTest
	public void afterTest() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.quit();
		
	}

}
