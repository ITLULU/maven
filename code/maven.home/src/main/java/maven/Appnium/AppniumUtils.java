package maven.Appnium;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import util.FileUtil;

public class AppniumUtils {

	public static AndroidDriver<WebElement> driver;
	public static Logger log = Logger.getLogger(AppniumUtils.class);

	/**
	 * 初始化
	 * 
	 * @return
	 */
	public static AndroidDriver<WebElement> Init() {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "ELE-AL00");
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "10");
		desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
		desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
		desiredCapabilities.setCapability("appWaitDuration", "50000");
		desiredCapabilities.setCapability("appWaitActivity", ".common.MainActivity");
		desiredCapabilities.setCapability("noReset", "True");
		try {
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<WebElement>(url, desiredCapabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}

	/**
	 * 应用打开时
	 * 
	 * @throws InterruptedException
	 */
	public static void TestPageOpen() {
		// 如果出现页面弹窗问题，展示弹窗页面元素
		String pagesource = driver.getPageSource();
//		log.info("展示页面元素："+pagesource);
		if (pagesource.contains("com.xueqiu.android:id/tv_title")) {
			closeClickOnWindow("com.xueqiu.android:id/tv_dis_agree");
		}
		searcher("com.xueqiu.android:id/home_search");

	}

	public static boolean closeAllOtherWindows(String openWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String currentWindowHandle : allWindowHandles) {
			if (!currentWindowHandle.equals(openWindowHandle)) {
				driver.switchTo().window(currentWindowHandle);
				driver.close();
			}
		}
		driver.switchTo().window(openWindowHandle);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	/**
	 * 窗口关闭
	 * 
	 * @param id
	 */
	public static void closeClickOnWindow(String id) {
		log.info("关闭协议提示");
		WebElement element = driver.findElementById(id);
		element.click();

	}

	/**
	 * 首页的搜索
	 * 
	 * @param id
	 */
	public static void searcher(String id) {
		WebElement element = driver.findElementById(id);
		element.click();
	}
	/**
	 * 截屏操作
	 * @param url
	 * @throws IOException 
	 */
	public static void takeshot(String url) throws IOException {
		File screenShotFile =driver.getScreenshotAs(OutputType.FILE);
		FileUtil.copyFile(screenShotFile,new File("./target/"+setScreenShotName(url)+".jpg"));
	}
	/**
	 * 截取图片名称
	 * @param url
	 * @return
	 */
	private static String setScreenShotName(String url) {
		// TODO Auto-generated method stub
	   String[] file=url.split("\\.");
	   String filename=file[file.length-1];
	   return filename;
	}
}
