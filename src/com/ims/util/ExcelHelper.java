package com.ims.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfinal.plugin.activerecord.Db;

public class ExcelHelper {
	
	public static HSSFWorkbook getNewWorkbook(){
		return new HSSFWorkbook();
	}
	public static void writeExcel(File file,HSSFWorkbook hssfWorkbook){
		  try {  
	            FileOutputStream fileOutputStreane = new FileOutputStream(file);  
	            hssfWorkbook.write(fileOutputStreane);  
	            fileOutputStreane.flush();  
	            fileOutputStreane.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	}

	public static void buildSheet(String[] s,String sql, String sheetName,HSSFWorkbook hssfWorkbook) {  
		int count = 0;
        // 创建工作薄  
        // sheet:一张表的简称  
        // row:表里的行  
        // 创建工作薄中的工作表  
        HSSFSheet hssfSheet = hssfWorkbook.createSheet(sheetName);  
        // 创建行  
        HSSFRow row = hssfSheet.createRow(0);  
        // 创建单元格，设置表头 创建列  
        HSSFCell cell = null;  
        
        //便利表头  
        for (int i = 0; i < s.length; i++) {  
            //创建传入进来的表头的个数  
            cell = row.createCell(i);  
            //表头的值就是传入进来的值  
            cell.setCellValue(s[i]);  
  
        }  
       
  
        List<Object[]> list = Db.query(sql);
      //获取所有的记录 有多少条记录就创建多少行  
        for (int i = 0; i < list.size(); i++) {  
            //row = hssfSheet.createRow(++count);  
            // 得到所有的行 一个record就代表 一行  
            //在有所有的记录基础之上，便利传入进来的表头,再创建N行
        	count++;
        	HSSFRow rowData = hssfSheet.createRow(count);  
            for (int j = 0; j < s.length; j++) {  
            	 //新增一个行就累加  
                cell = rowData.createCell(j); 
                if(list.get(i)[j] == null)
                	cell.setCellValue("");
                else
                //把每一行的记录再次添加到表头下面 如果为空就为 "" 否则就为值 
                cell.setCellValue(list.get(i)[j].toString());
            }  
        }  
        
    }  
}
