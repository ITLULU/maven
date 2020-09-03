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
  String filePath="D:\\appach\\��Ʒ��Ϣ.xlsx";
  String sheetName="��Ʒ��Ϣ";
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
			System.out.println("��"+(i+1)+"�е�"+(j+1)+"�е�ֵ��"+ob[i][j]);
		 }
	 }
  }
  
  @Feature("ǰ��ͻ������")
  public class Test01 {

      @Test(description = "��������")
      @Story("���Է�ȯ")
      @Description("��Ҫ��������ȯ����")
      @Step("���Բ���....")
      public void failedTest(){
          Assert.assertEquals(2,2);
      }
  }

}
