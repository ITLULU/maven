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
	 * ��ҳ������
	 * 
	 * @param id
	 */
	public static void searcher(String id) {
		
		WebElement element = driver.findElementById(id);
		element.click();
	}
	
	/**
	 * Ӧ�ô�ʱ
	 * 
	 * @throws InterruptedException
	 */
	public static void TestPageOpen() {
		// �������ҳ�浯�����⣬չʾ����ҳ��Ԫ��
		String pagesource = driver.getPageSource();
//		log.info("չʾҳ��Ԫ�أ�"+pagesource);
		if (pagesource.contains("com.xueqiu.android:id/tv_title")) {
			closeClickOnWindow("com.xueqiu.android:id/tv_dis_agree");
		}
		searcher("com.xueqiu.android:id/home_search");

	}

	
	/**
	 * ���ڹر�
	 * 
	 * @param id
	 */
	public static void closeClickOnWindow(String id) {
		log.info("�ر�Э����ʾ");
		WebElement element = driver.findElementById(id);
		element.click();

	}
	
	/**
	 * id�鿴
	 * @param id
	 */
	public static void testFindElementById(String id) {
		driver.findElement(By.id(id));
	}
	/**
	 * ͨ��classname����
	 * @param classname
	 */
	public static void testfindElementByClassName(String className) {
//		driver.findElementByClassName(className);
		List<WebElement>webElements= driver.findElements(By.className(className));
	}
	/**
	 * cssSlector�鿴
	 * @param CssSelector
	 */
	public static void testfindElementByCssSelector(String CssSelector) {
		driver.findElementByCssSelector(CssSelector);
	}
	/**
	 * �÷�ʽjava-clent4.0���е�
	 * @param ContentDesc
	 */
//	public static void testfindElementByContentDesc(String usiContentDescng) {
//		/**
//		 * usiContentDescng��new UiSelector().description(\"�ҵ����ʰ�\");
//		 */
//		driver.findElementByAndroidUIAutomator(usiContentDescng);
//	}
	/**
	 * xpath���·������
	 * @param relative
	 */
	public static void testfindElementByRelative(String relative) {
		WebElement fatherElement=driver.findElement(By.id("com.lemon:id/smalLabel"));
		fatherElement.findElement(By.className("andriod.view.view"));
	}
	/**
	 * ͨ���ı�����
	 * UiAutomator��ԭ����UIAutomator���ȥ��Ԫ��
	 * ͨ��UiAutomator��descripation�����ҵ�����Ϊcontent-desc��Ԫ��
	 * usiContentDescng��new UiSelector().description(\"�ҵ����ʰ�\");
	 * driver.findElementByAndroidUIAutomator(usiContentDescng);
	 * 
	 * ͨ��UiAutomator��text�����ҵ�����Ϊtext��Ԫ��
	 * driver.findElementByAndroidUIAutomator("new UiSelector().text(\"ȫ�̰�\")").click();
	 * 
	 * ͨ��UiAutomator��resourceId�����ҵ�����ΪresourceId��Ԫ��
	 * driver.findElementByAndroidUIAutomator("new UiSelector().resourceId(\"com.lemon:id/smalLabel\")");
	 * @param relative
	 */
//	public static void testfindElementByText(String relative) {
//		driver.findElementByAndroidUIAutomator("new UiSelector().text(\"ȫ�̰�\")").click();
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
