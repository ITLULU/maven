package maven.test;

import org.testng.annotations.Test;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import util.ExcelUtils;

import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestMavenExcel {
 
  Logger log=Logger.getLogger(TestMavenExcel.class);
  String filePath="D:\\appach\\产品信息.xlsx";
  String sheetName="产品信息";
  @BeforeTest
  public void beforeTest() {
	  log.info("----beforeTest----");
  }

  @AfterTest
  public void afterTest() {
  }
  @Test
  public void f() throws EncryptedDocumentException, IOException {
	 List<Map<String,String>>lists= ExcelUtils.readExcel(filePath, sheetName);
	 ExcelUtils.printExcelData(lists);
	 Object[][]ob=ExcelUtils.getExcelData(lists);
	 for(int i=0;i<ob.length;i++) {
		 for(int j=0;j<ob[i].length;j++) {
			System.out.println("第"+(i+1)+"行第"+(j+1)+"列的值："+ob[i][j]);
		 }
	 }
  }
  
  @Feature("前线突击测试")
  public class Test01 {

      @Test(description = "侯征测试")
      @Story("测试发券")
      @Description("主要测试四种券发送")
      @Step("测试步骤....")
      public void failedTest(){
          Assert.assertEquals(2,2);
      }
  }

}
