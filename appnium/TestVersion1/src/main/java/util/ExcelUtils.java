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
import org.apache.poi.util.StringUtil;

/**
 * Excel工具类
 * @author Administrator
 *
 */
public class ExcelUtils {

	private static Logger log=Logger.getLogger(ExcelUtils.class);
	/**
	 * 读取
	 * @return
	 * @throws IOException 
	 * @throws EncryptedDocumentException 
	 */
	public static List<Map<String ,String>>readExcel(String filePath,String sheetName) throws EncryptedDocumentException, IOException {
		List<Map<String, String>> list = new LinkedList<Map<String,String>>();
		List<String>listname=new LinkedList<String>();
		
		File file=new File(filePath);
		if(!file.exists()) {
		   log.info("文件没有查找到");
		   throw new FileNotFoundException("没有发现文件");
		}
		InputStream ins=new FileInputStream(new File(filePath));
		Workbook workbook=WorkbookFactory.create(ins);
		Sheet sheet=workbook.getSheet(sheetName);
		//得到行数
		int rownum=sheet.getLastRowNum();
		log.info("一共"+rownum+"行");
		//得到第一行
		Row row=sheet.getRow(0);
		//得到总共的列数
		int colnum=row.getLastCellNum();
		log.info("共"+colnum+"列");
		for(int i=0;i<colnum;i++) {
			//得到每一列的对应的名称
			Cell cell=row.getCell(i);
			listname.add(i, getCellFormatValue(cell));
		}
		log.info("Excel表头名称："+listname.toString());
		for(int i=1;i<=rownum;i++) {
			Row lrow=sheet.getRow(i);
			Map<String,String>map=new LinkedHashMap<String, String>();
			for(int j=0;j<colnum;j++) {
				Cell ccell=lrow.getCell(j);
				//log.info("第"+(i+1)+"行第"+(j+1)+"列信息："+ccell);
				map.put(listname.get(j), getCellFormatValue(ccell));
			}
			list.add(map);
		}
	    log.info("获取的Excel信息："+list);
		return list;
	}
	/**
	 * 得到单个列的数据信息
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
	 * 输出Excel信息
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
	 * 得到ob对象
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
}
