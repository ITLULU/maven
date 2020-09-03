package maven.Appnium;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
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
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import util.FileUtil;

public class AppniumUtils {

	public static AndroidDriver<WebElement> driver;
	public static Logger log = Logger.getLogger(AppniumUtils.class);

	/**
	 * ��ʼ��
	 * ���Ե���ѩ��app
	 * ��Ҫ��һ����װ���Զ���app
	 * @return
	 * @throws InterruptedException 
	 */
	public static AndroidDriver<WebElement> Init() throws InterruptedException {
		DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
		desiredCapabilities.setCapability("deviceName", "ELE-AL00");
		desiredCapabilities.setCapability("platformName", "Android");
		desiredCapabilities.setCapability("platformVersion", "10");
		desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
		desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
		desiredCapabilities.setCapability("appWaitDuration", "50000");
		desiredCapabilities.setCapability("appWaitActivity", ".common.MainActivity");
		desiredCapabilities.setCapability("noReset", "false");
		desiredCapabilities.setCapability("unicodeKeyboard", "True");
		try {
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			driver = new AndroidDriver<WebElement>(url, desiredCapabilities);
			Thread.sleep(10);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
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
	 * ��������
	 * 
	 * @param url
	 * @throws IOException
	 */
	public static void takeScreenShot(String url) throws IOException {
		File screenShotFile = driver.getScreenshotAs(OutputType.FILE);
		FileUtil.copyFile(screenShotFile, new File("./target/" + setScreenShotName(url) + ".jpg"));
	}

	/**
	 * ��ȡͼƬ����
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


	/**
	 * �ƶ� ��������
	 * @param startx
	 * @param starty
	 * @param endx
	 * @param endy
	 * @param duration
	 * swipe������4.0 java-client����ʹ��
	 * ��5.0�п��Բ�ȡ�Զ���ķ���ʵ��swipe
	 */
	public static void swipe(int startx,int starty,int endx,int endy ,int time) {
//		TouchAction touchAction =new TouchAction(driver);
//		touchAction.press(startx,starty).waitAction(time).moveTo(endx,endy).release();
//		touchAction.perform();
		
		TouchAction touchAction =new TouchAction(driver);
		Duration duration=Duration.ofMillis(time);
		touchAction.press(startx,starty).waitAction(duration).moveTo(endx,endy).release();
		touchAction.perform();
	}
	/**
	 * ���� ���ϣ����»���
	 * @param duration
	 */
	public  void PressSwipe(int durationl) {
		int xwidth=driver.manage().window().getSize().width;
		int yheight=driver.manage().window().getSize().height;
		/**
		 * ���»���
		 * 4.0�汾
		 */
		//driver.swipe(width/2, height*3/4, width/2, height/4, duration);
		int startx=xwidth/2;
		int starty=yheight/4;
		
		int endx=xwidth/2;
		int endy=3*yheight/4;
		
		
		TouchAction touchAction =new TouchAction(driver);
		Duration durationl1=Duration.ofMillis(300);
		touchAction.press(startx,starty).waitAction(durationl1).moveTo(endx,endy).release();
		touchAction.perform();
		
	}
	/**
	 * ͨ�û��������󻬶�
	 */
	public  void swipeLeft() {
		int xwidth=driver.manage().window().getSize().width;
		int yheight=driver.manage().window().getSize().height;
		int startx=3*xwidth/4;
		int starty=yheight/2;
		
		int endx=xwidth/4;
		int endy=yheight/2;
		
		TouchAction touchAction =new TouchAction(driver);
		Duration duration=Duration.ofMillis(300);
		touchAction.press(startx,starty).waitAction(duration).moveTo(endx,endy).release();
		touchAction.perform();
		
	}
	/**
	 * ��������
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
	 * ��������
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
	 * ��������Ӧ��
	 * @param xpath
	 * @param appPackage
	 * @param appActivity
	 */
	public static void OpenOtherApplication(String xpath,String appPackage,String appActivity) {
		MobileElement mobileElement=(MobileElement) driver.findElement(By.xpath(xpath));
		mobileElement.click();
		/**
		 * 4.0�汾����
		 */
//		driver.startActivity(appPackage, appActivity);
	}
	
/*	public static void startOtherActivity(String activity,String appActivity) {
		Activity activity=new Activity(appPackage, appActivity);
		driver.startActivity(activity);
	}*/
	
	/**
	 * �����϶�
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
	 * ����������
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
