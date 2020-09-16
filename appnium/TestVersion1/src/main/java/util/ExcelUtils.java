package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.util.StringUtil;

/**
 * Excel������
 * @author Administrator
 *
 */
public class ExcelUtils {

	private static Logger log=Logger.getLogger(ExcelUtils.class);
	/**
	 * ��ȡ
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static List<Map<String ,String>>readExcel(String filePath,String sheetName) throws EncryptedDocumentException, IOException {
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		List<String>listname=new LinkedList<String>();
		
		File file=new File(filePath);
		if(!file.exists()) {
		   log.info("�ļ�û�в��ҵ�");
		   throw new FileNotFoundException("û�з����ļ�");
		}
		InputStream ins=new FileInputStream(new File(filePath));
		Workbook workbook=WorkbookFactory.create(ins);
		Sheet sheet=workbook.getSheet(sheetName);
		//�õ�����
		int rownum=sheet.getLastRowNum();
		log.info("һ��"+rownum+"��");
		//�õ���һ��
		Row row=sheet.getRow(0);
		//�õ��ܹ�������
		int colnum=row.getLastCellNum();
		log.info("��"+colnum+"��");
		for(int i=0;i<colnum;i++) {
			//�õ�ÿһ�еĶ�Ӧ������
			Cell cell=row.getCell(i);
			listname.add(i, getCellFormatValue(cell));
		}
		log.info("Excel��ͷ���ƣ�"+listname.toString());
		for(int i=1;i<=rownum;i++) {
			Row lrow=sheet.getRow(i);
			Map<String,String>map=new LinkedHashMap<String, String>();
			for(int j=0;j<colnum;j++) {
				Cell ccell=lrow.getCell(j);
				//log.info("��"+(i+1)+"�е�"+(j+1)+"����Ϣ��"+ccell);
				map.put(listname.get(j), getCellFormatValue(ccell));
			}
			list.add(map);
		}
	    log.info("��ȡ��Excel��Ϣ��"+list);
		return list;
	}
	/**
	 * �õ������е�������Ϣ
	 * @param cell
	 * @return
	 */
	public static String getCellFormatValue(Cell cell) {
		if(cell!=null&&!StringUtils.isBlank(cell.toString())) {
			 cell.setCellType(CellType.STRING);
			 return cell.getStringCellValue();
		}
		return "";
		
	}

	/**
	 * ���Excel��Ϣ
	 * @param datas
	 */
	public static void  printExcelData( List<Map<String ,String>>datas) {
		for(int i=0;i<datas.size();i++) {
			Map<String ,String>map=new LinkedHashMap<String, String>();
			map=datas.get(i);
			Iterator<?> it=map.entrySet().iterator();
			while(it.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry entry = (Map.Entry)it.next();
				String key=String.valueOf(entry.getKey());
				String value=String.valueOf(entry.getValue());
			}
		}
	}
	/**
	 * �õ�����
	 * @param fileName
	 * @param sheetName
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static String[] getTitle(String fileName,String sheetName) throws EncryptedDocumentException, IOException {
		InputStream ins=new FileInputStream(new File(fileName));
		Workbook workbook=WorkbookFactory.create(ins);
		Sheet sheet=workbook.getSheet(sheetName);
		Row titile=sheet.getRow(0);
		//�õ����һ�е��к�
		int lastColum=titile.getLastCellNum();
		String[]titleList=new String[lastColum];
		for(int i=0;i<lastColum;i++) {
			Cell cell=titile.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value=cell.getStringCellValue();
			value=value.substring(0,value.indexOf("("));
			titleList[i]=value;
		}
		return titleList;
	}
	
	/**
	 * �õ�ob����
	 * @param datas
	 * @return
	 */
	public static Object[][] getExcelData(List<Map<String ,String>>datas){
		int length=datas.size();
		int len=datas.get(0).size();
		log.info("length:"+length+"len:"+len);
		Object [][] ob=new Object[length][len];
		for(int i=0;i<length;i++) {
			int j=0;
			Map<String ,String>map=datas.get(i);
			for(String key:map.keySet()) {
				ob[i][j++]=map.get(key);
			}
		}
		return ob;
	}
	/**
	 * �õ�֪�������Excel����
	 * @param fileName
	 * @param sheetName
	 * @param rowloum
	 * @param coloum
	 * @return
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static Object[][] ReadExcelTo(String fileName,String sheetName,int rowloum[],int coloum[]) throws EncryptedDocumentException, IOException {
		Object[][] ob=new Object[rowloum.length][coloum.length];
		InputStream ins=new FileInputStream(new File(fileName));
		Workbook workbook=WorkbookFactory.create(ins);
		String title[]=getTitle(fileName, sheetName);
		Sheet sheet=workbook.getSheet(sheetName);
		for(int i=0;i<rowloum.length;i++) {
			Row row=sheet.getRow(rowloum[i]-1);
			for(int j=0;j<coloum.length;j++) {
				String titlename=title[j];
				Cell cell=row.getCell(coloum[j]-1,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				//log.info(cell.getStringCellValue());
				ob[i][j]=titlename+":"+cell.getStringCellValue();
			}
			
		}
		return ob;
	}
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		  String fileName="src/main/resources/TestExcel.xlsx";
		  String sheetName="main";
		  String[]titile= getTitle(fileName, sheetName);
		  for(String str:titile) {
			  log.info("key��"+str);
		  }
		  
		  int row[]= {2,3};
		  int col[]= {1,2,3};
		  Object[][] obj=ReadExcelTo(fileName, sheetName,row,col);
		  for(int i=0;i<obj.length;i++) {
			  for(int j=0;j<obj[i].length;j++) {
				  System.out.println("value:"+obj[i][j]);
			  }
		  }
		  
	}
}
