package maven.test;

import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import pojo.User;
import util.ExcelUtils;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class TestMavenExcel {
 
  Logger log=Logger.getLogger(TestMavenExcel.class);
  String fileName="src/main/resources/TestExcel.xlsx";
  String sheetName="main";
  @BeforeTest
  public void beforeTest() {
	  log.info("----beforeTest----");
  }

  @AfterTest
  public void afterTest() {
  }
/*  @Test
  public void f() throws EncryptedDocumentException, IOException {
	 List<Map<String,String>>lists= ExcelUtils.readExcel(filePath, sheetName);
	 ExcelUtils.printExcelData(lists);
	 Object[][]ob=ExcelUtils.getExcelData(lists);
	 for(int i=0;i<ob.length;i++) {
		 for(int j=0;j<ob[i].length;j++) {
			System.out.println("第"+(i+1)+"行第"+(j+1)+"列的值："+ob[i][j]);
		 }
	 }
  }*/
  @Test(dataProvider="datas")
  public void TestExcel(String parameter) {
	  log.info("parameter："+parameter);
//	  User user=JSONObject.parseObject(parameter,User.class);
//	  log.info("user:"+user);
	  Map<String,String>map=JSONObject.parseObject(parameter,Map.class);
  }
  @DataProvider
  public Object[][]datas(){
	  int row[]= {2,3};
	  int col[]= {1,2,3};
	  Object[][] obj=null;
	  try {
		  obj=ExcelUtils.ReadExcelTo(fileName, sheetName,row, col);
	} catch (EncryptedDocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return obj;
  }
  
  /*@Feature("前线突击测试")
  public class Test01 {

      @Test(description = "侯征测试")
      @Story("测试发券")
      @Description("主要测试四种券发送")
      @Step("测试步骤....")
      public void failedTest(){
          Assert.assertEquals(2,2);
      }
  }*/

}
