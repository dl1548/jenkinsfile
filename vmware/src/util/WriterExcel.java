package util;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

public class WriterExcel {

	public void writerexcel(Map<String, Object> map,HSSFWorkbook wb,String type){
		Sheet sheet=wb.createSheet();
		Row row0=sheet.createRow(0);
		row0.createCell(0).setCellValue("方法名");
		row0.createCell(1).setCellValue("值");
		row0.createCell(2).setCellValue("类型："+type);
		Set<String> set=map.keySet();
		String[] str=set.toArray(new String[set.size()]);
		System.out.println("共有"+str.length+"个方法值");
		for(int i=0;i<str.length;i++){
			Row row=sheet.createRow(i+1);
			String key=str[i];
			String value="";
			if(i==336){
				System.out.println(0);
			}
			if(null!=map.get(key)){
				if(null==map.get(key)){
					System.out.println("有null值");
				}
				System.out.println(key);
				try {
					value=map.get(key).toString();
				} catch (Exception e) {
				}
			}
			row.createCell(0).setCellValue(key);
			row.createCell(1).setCellValue(value);
		}
	}
}
