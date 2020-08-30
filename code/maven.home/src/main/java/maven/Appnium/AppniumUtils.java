package maven.Appnium;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;



import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import util.FileUtil;

public class AppniumUtils {

	public static AndroidDriver<WebElement> driver;
	public static Logger log = Logger.getLogger(AppniumUtils.class);

	/**
	 * 初始化
	 * 测试的是雪球app
	 * 需要进一步封装测试对象app
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
		desiredCapabilities.setCapability("unicodeKeyboard", "True");
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
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void takeScreenShot(String url) throws IOException {
		File screenShotFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtil.copyFile(screenShotFile, new File("./target/" + setScreenShotName(url) + ".jpg"));
	}

	/**
	 * 截取图片名称
	 * 
	 * @param url
	 * @return
	 */
	private static String setScreenShotName(String url) {
		// TODO Auto-generated method stub
		String[] file = url.split("\\.");
		String filename = file[file.length - 1];
		return filename;
	}

	@SuppressWarnings("unused")
	private static String TestAutomator(String id) {
		@SuppressWarnings("unused")
		List<WebElement> elements = (List<WebElement>) driver.findElementByAndroidUIAutomator("new Uiselector().className(" + id + ")");
		List<WebElement> elementsinsdex = (List<WebElement>) driver.findElementByAndroidUIAutomator("new Uiselector().className(" + id + ").index(1)");

		WebElement webElement = elements.get(0);
		String value = webElement.getText();
		return value;
	}
	/**
	 * 移动 滑动操作
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param duration
	 */
	public static void swipe(int startx,int starty,int endx,int endy ,int duration) {
		TouchAction touchAction =new TouchAction(driver);
		touchAction.press(startx,starty).waitAction(duration).moveTo(endx,endy).release();
		touchAction.perform();
	}
	/**
	 * 滑动 向上，向下滑动
	 * @param duration
	 */
	public static void PressSwipe(int duration) {
		int width=driver.manage().window().getSize().width;
		int height=driver.manage().window().getSize().height;
		/**
		 * 向下滑动
		 */
		driver.swipe(width/2, height*3/4, width/2, height/4, duration);
	}
	/**
	 * 滑动操作
	 */
	public void ScrollToElement(String str) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true),instance(0)).ScrollIntoView(new UiSelector().textContains(\""
						+ str + "\").instance(0))");
	}
	public void ScrollToExactElement(String str) {
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector().scrollable(true),instance(0)).ScrollIntoView(new UiSelector().text(\""
						+ str + "\").instance(0))");
	}
	/**
	 * 处理缩放
	 * @param x
	 * @param y
	 */
	public void zoom(int x,int y) {
		MultiTouchAction multiTouchAction=new MultiTouchAction(driver);
		int scrHeight=driver.manage().window().getSize().getHeight();
		int yOffset=100;
		if(y-100<0) {
			yOffset=y;
		}else if(y+100>scrHeight) {
			yOffset=scrHeight-y;
		}
		TouchAction action0=new TouchAction(driver).press(x,y).moveTo(x,y-yOffset).release();
		TouchAction action1=new TouchAction(driver).press(x,y).moveTo(x,y+yOffset).release();
		multiTouchAction.add(action0).add(action1);
		multiTouchAction.perform();
	}
	
	/**
	 * 开启其他应用
	 * @param xpath
	 * @param appPackage
	 * @param appActivity
	 */
	public static void OpenOtherApplication(String xpath,String appPackage,String appActivity) {
		MobileElement mobileElement=(MobileElement) driver.findElement(By.xpath(xpath));
		mobileElement.click();
		
		driver.startActivity(appPackage, appActivity);
	}
	/**
	 * 处理拖动
	 * @param StartElemnt
	 * @param EndElement
	 */
	public void drag(By StartElemnt,By EndElement) {
		TouchAction touchAction=new TouchAction(driver);
		MobileElement start=(MobileElement) driver.findElement(StartElemnt);
		MobileElement end=(MobileElement) driver.findElement(EndElement);
		touchAction.press(start).perform();
		touchAction.moveTo(end).release().perform();
	}
	/**
	 * 截屏并保存
	 * @param webElement
	 * @throws IOException
	 */
	public void getElementShotSaveASImage(WebElement webElement) throws IOException {
		File screenShot=driver.getScreenshotAs(OutputType.FILE);
		BufferedImage img=ImageIO.read(screenShot);
		int width=webElement.getSize().getWidth();
		int height=webElement.getSize().height;
		Rectangle rectangle=new Rectangle(width,height);
		Point point= webElement.getLocation();
		BufferedImage dest=img.getSubimage(point.x, point.y, rectangle.width, rectangle.height);
		ImageIO.write(dest, "png", screenShot);
	}
}
