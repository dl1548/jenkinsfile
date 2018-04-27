package com.jk.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zabbix.api.domain.base.Item;
import com.zabbix.api.domain.history.HistoryGetRequest;
import com.zabbix.api.domain.item.ItemGetRequest;
import com.zabbix.api.service.IHistoryService;
import com.zabbix.api.service.IItemService;
import com.zabbix.api.service.impl.HistoryServiceImpl;
import com.zabbix.api.service.impl.ItemServiceImpl;
import com.zabbix.util.FormatData;
import com.zabbix4j.ZabbixApiException;
import com.zabbix4j.host.Host;
import com.zabbix4j.host.HostGetRequest;
import com.zabbix4j.host.HostGetResponse;

public class HostInfoControllerSevice {
	private static IHistoryService IHistoryService=new HistoryServiceImpl();
	

	public String getCycSelete(String hostid, String[] startTime,String[] endTime, String itemkey
			,String auth) throws JSONException {
		Item item=getItemid(itemkey,hostid, auth);
		if(item==null)return null;
		JSONArray jsonarray=new JSONArray();
		for(int i=0;i<startTime.length;i++){
			JSONObject jsonobject=new JSONObject();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			long start = 0;
			long end = 0;
			try {
				start = sdf.parse(startTime[i]).getTime()/1000;
				end=sdf.parse(endTime[i]).getTime()/1000; 
			} catch (ParseException e) {
				e.printStackTrace();
			}
				 HistoryGetRequest hrequest=new HistoryGetRequest(auth);
				 List<String> sortfile=new ArrayList<>();
				 sortfile.add("clock");
				 List<String> desc=new ArrayList<>();
				 desc.add("DESC");
			    	List<String> hostids=new ArrayList<>();
			    	hostids.add(hostid);
					hrequest.getParams().setHostids(hostids);
					List<String> itemids = new ArrayList<>();
					itemids.add(item.getItemid());
					hrequest.getParams().setItemids(itemids);
					hrequest.getParams().setHistory(item.getValue_type());
					hrequest.getParams().setSortfield(sortfile);
					hrequest.getParams().setSortorder(desc);
					hrequest.getParams().setTime_from(start);
					hrequest.getParams().setTime_till(end);
					String units = item.getUnits()=="bps"?"kbps":item.getUnits();
					JSONObject object=(JSONObject)IHistoryService.get(hrequest, auth);
					 jsonobject.put("value",object);
					 jsonobject.put("hostid", hostid);
					 jsonobject.put("units", units==null?"":units);
					 jsonarray.put(jsonobject);
				 }
					return jsonarray.toString();
	}
	public  Item getItemid(String itemkey,String hostid,String auth){
		IItemService itemService = new ItemServiceImpl();
		ItemGetRequest request=new ItemGetRequest(auth);
		request.getParams().setOutput("extend");
	    request.getParams().setHostids(new String[]{hostid});
		List<Item> items = itemService.getItem(request, auth);
		if(items==null)return null;
		for(Item item:items){
			System.out.println(item.getKey_());
			if(item.getKey_().equals(itemkey))return item;
		}
		return null;
	}
	public  Item getItemById(String itemid,String hostid,String auth){
		IItemService itemService = new ItemServiceImpl();
		ItemGetRequest request=new ItemGetRequest(auth);
		request.getParams().setOutput("extend");
	    request.getParams().setHostids(new String[]{hostid});
	    request.getParams().setItemids(new String[]{itemid});
		List<Item> items = itemService.getItem(request, auth);
		Iterator<Item> iterator=items.iterator();
		while(iterator.hasNext()){
			Item item=iterator.next();
			return item;
		}
		return null;
	}
	public void writerExcel (String second,String type,HttpServletResponse response) throws IOException, JSONException {
		System.out.println(second);
		JSONArray arry=new JSONArray("["+second+"]");
		HSSFWorkbook wb = new HSSFWorkbook();
		Sheet sheet=wb.createSheet("查询结果");
		sheet.addMergedRegion(new CellRangeAddress(0,1,0,2));
		HSSFCellStyle setBorber=wb.createCellStyle();
		setBorber.setFillForegroundColor(new HSSFColor.GREY_25_PERCENT().getIndex());
		setBorber.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		setBorber.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		setBorber.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		setBorber.setBorderTop(HSSFCellStyle.BORDER_THIN);
		setBorber.setBorderRight(HSSFCellStyle.BORDER_THIN);
		setBorber.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		setBorber.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFCellStyle setBorber2=wb.createCellStyle();
		setBorber2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		setBorber2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont font=wb.createFont();
		font.setFontName("黑体");
		font.setFontHeightInPoints((short)16);
		
		HSSFFont font2=wb.createFont();
		font2.setFontName("仿宋_GB2312");
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font2.setFontHeightInPoints((short)12);
		setBorber.setFont(font2);
		Row row=sheet.createRow(0);
		Cell cell=row.createCell(0);
		cell.setCellValue("主机");
		cell.setCellStyle(setBorber);
		Row row1=sheet.createRow(1);
		 // 生成文件 
		boolean addHeaderStyle=true;
		Set<String> set=new HashSet<>();
		for(int i=0;i<arry.length();i++){
			int flag=3;
			JSONObject json=arry.getJSONObject(i);
			String hostname=json.getString("hostname");
				Row rows=sheet.createRow(i+2);
				sheet.addMergedRegion(new CellRangeAddress(i+2,i+2,0,2));
				Cell cells=rows.createCell(0);
				cells.setCellValue(hostname);
				cells.setCellStyle(setBorber2);
				JSONArray jsonarry=json.getJSONArray("item");
				for(int j=0;j<jsonarry.length();j++){
				JSONObject obj=jsonarry.getJSONObject(j);//获取主机下object对象
				String max=obj.getString("max");
				String min=obj.getString("min");
				String avg=obj.getString("avg");
				String itemname=obj.getString("itemname");
				addHeaderStyle=!set.contains(itemname);
				if(addHeaderStyle){
						set.add(itemname);
					    sheet.addMergedRegion(new CellRangeAddress(0,0,flag,flag+2));
						Cell cel=row.createCell(flag);
						cel.setCellValue(itemname);
						cel.setCellStyle(setBorber);
						Cell cell13=row1.createCell(flag);
						cell13.setCellValue("最大值");
						Cell cell14=row1.createCell(flag+1);
						cell14.setCellValue("最小值");
						Cell cell15=row1.createCell(flag+2);
						cell15.setCellValue("平均值");
						addHeaderStyle=false;
						}
							rows.createCell(flag).setCellValue(max);
							rows.createCell(flag+1).setCellValue(min);
							rows.createCell(flag+2).setCellValue(avg);
						flag+=3;
				}
		}
		long name=System.currentTimeMillis();
		String path=this.getClass().getResource("/").getPath()+name+"."+type;
        FileOutputStream fileOut = new FileOutputStream(path);
        wb.write(fileOut);
        fileOut.close();
        InputStream in = null;
        try {  
            ByteArrayOutputStream os = new ByteArrayOutputStream();  
            wb.write(os);  
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");  
            response.setHeader("Content-Disposition", "attachment;filename="+name+"."+type);  
            ServletOutputStream out = response.getOutputStream();
            in = new FileInputStream(new File(path));
            int b;
            while((b=in.read())!=-1){
                out.write(b);
            }
        } catch (Exception e) {  
        	e.printStackTrace();
        }finally {  
            try {  
                if(in!=null){
                	in.close();
                }
            } catch (IOException e) {  
                   e.printStackTrace();
              }  
        }
        
        
        //wb.write(fileOut);  
      //  fileOut.close();
//        response.setContentType("multipart/form-data");
//        response.setHeader("Content-Disposition", "attachment;filename=test.xls");
//        OutputStream out = response.getOutputStream();
//        InputStream in = new FileInputStream(new File(path));
//        int b;
//        while((b=in.read())!=-1){
//            out.write(b);
//        }
//        in.close();
//        out.close();
	}
	public String[] hostGetByGroupId(Integer hostGroupId,String auth) throws JSONException, ZabbixApiException {
		 Host host=new Host(FormatData.API_URL,auth);
		 HostGetRequest request = new HostGetRequest();
		 List<Integer> plist=new ArrayList<>();
	     plist.add(hostGroupId);
	     request.getParams().setGroupids(plist);
		 HostGetResponse response=host.get(request);
		 List<com.zabbix4j.host.HostGetResponse.Result> list=response.getResult();
		 String[] ids=new String[list.size()];
		 for(int i=0;i<list.size();i++){
			 com.zabbix4j.host.HostGetResponse.Result result=list.get(i);
			 String hostid=result.getHostid().toString();
			 System.out.println("result.getName"+result.getName());
			 ids[i]=hostid;
		 }
	     return ids; 
	    }
}
