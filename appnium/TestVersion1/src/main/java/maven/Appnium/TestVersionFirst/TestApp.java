package maven.Appnium.TestVersionFirst;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.android.AndroidDriver;
import maven.Appnium.AppniumUtils;

public class TestApp {

	public static AndroidDriver<WebElement> driver;
	public static Logger log = Logger.getLogger(TestApp.class);
	static {
		try {
			driver=AppniumUtils.Init();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * id查看
	 * @param id
	 */
	public static void testFindElementById(String id) {
		driver.findElement(By.id(id));
	}
	/**
	 * 通过classname查找
	 * @param classname
	 */
	public static void testfindElementByClassName(String className) {
//		driver.findElementByClassName(className);
		List<WebElement>webElements= driver.findElements(By.className(className));
	}
	/**
	 * cssSlector查看
	 * @param CssSelector
	 */
	public static void testfindElementByCssSelector(String CssSelector) {
		driver.findElementByCssSelector(CssSelector);
	}
	/**
	 * 该方式java-clent4.0所有的
	 * @param ContentDesc
	 */
//	public static void testfindElementByContentDesc(String usiContentDescng) {
//		/**
//		 * usiContentDescng：new UiSelector().description(\"我的柠檬班\");
//		 */
//		driver.findElementByAndroidUIAutomator(usiContentDescng);
//	}
	/**
	 * xpath相对路径查找
	 * @param relative
	 */
	public static void testfindElementByRelative(String relative) {
		WebElement fatherElement=driver.findElement(By.id("com.lemon:id/smalLabel"));
		fatherElement.findElement(By.className("andriod.view.view"));
	}
	/**
	 * 通过文本查找
	 * UiAutomator是原生的UIAutomator框架去找元素
	 * 通过UiAutomator的descripation方法找到属性为content-desc的元素
	 * usiContentDescng：new UiSelector().description(\"我的柠檬班\");
	 * driver.findElementByAndroidUIAutomator(usiContentDescng);
	 * 
	 * 通过UiAutomator的text方法找到属性为text的元素
	 * driver.findElementByAndroidUIAutomator("new UiSelector().text(\"全程班\")").click();
	 * 
	 * 通过UiAutomator的resourceId方法找到属性为resourceId的元素
	 * driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.lemon:id/smalLabel\")");
	 * @param relative
	 */
//	public static void testfindElementByText(String relative) {
//		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"全程班\")").click();
//	}
	
	
	@SuppressWarnings("unused")
	private static String TestAutomator(String id) {
		@SuppressWarnings("unused")
		List<WebElement> elements = (List<WebElement>) driver.findElementByAndroidUIAutomator("new Uiselector().className(" + id + ")");
		List<WebElement> elementsinsdex = (List<WebElement>) driver.findElementByAndroidUIAutomator("new Uiselector().className(" + id + ").index(1)");

		WebElement webElement = elements.get(0);
		String value = webElement.getText();
		return value;
	}
}
