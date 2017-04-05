package com.ims.cron4j;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.ims.util.ExcelHelper;
import com.ims.util.StringUtil;
import com.ims.util.TimeUtil;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Db;

public class ExportExcelJob implements Runnable {
	private static Logger logger = Logger.getLogger(ExportExcelJob.class);
	
	@Override
	public void run() {
		logger.info("开始导出Excel任务***************");
		try {
			String sql = "select * from s_excel";
			logger.info(sql);
			List<Object[]> excels = Db.query(sql);
			logger.info("查询到excel导出任务" + excels.size() + "条");
			
			String month = TimeUtil.getMoth(null);
			String fileName = "出入库记录_" + month + ".xls";
			String dir = PropKit.get("excel_dir");
			if(!StringUtil.isNull(dir)){
				File dirFile = new File(dir);
				if(!dirFile.exists()){
					dirFile.mkdirs();
				}
			}
			String pathFile = dir + fileName;
			logger.info("开始生成文件" + pathFile);
			
			HSSFWorkbook  workBook = ExcelHelper.getNewWorkbook();
			//遍历任务
			for (Object[] objects : excels) {
				//新建sheeft
				ExcelHelper.buildSheet(objects[2].toString().split(","), 
						objects[4].toString(), objects[3].toString(), workBook);

			}
			
			ExcelHelper.writeExcel(new File(pathFile), workBook);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.info("结束导出Excel任务***************");
	}

}
