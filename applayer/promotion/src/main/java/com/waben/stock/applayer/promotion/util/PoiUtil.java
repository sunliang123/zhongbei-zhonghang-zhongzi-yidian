package com.waben.stock.applayer.promotion.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PoiUtil {

	private static Logger logger = LoggerFactory.getLogger(PoiUtil.class);

	public static void testMain(String[] args) {
		List<String> columnDescList = new ArrayList<>();
		columnDescList.add("序号");
		columnDescList.add("姓名");

		List<List<String>> dataList = new ArrayList<>();
		List<String> innerData = new ArrayList<>();
		innerData.add("1");
		innerData.add("zhangsan");
		dataList.add(innerData);
		writeDataToExcel("bbb", new File("D:\\test.xls"), columnDescList, dataList);

	}

	/**
	 * 将数据写入到excel文件中
	 * 
	 * @param file
	 *            文件
	 * @param columnDescList
	 *            列描述
	 * @param dataList
	 *            数据
	 */
	public static void writeDataToExcel(String sheetTitle, File file, List<String> columnDescList,
			List<List<String>> dataList) {
		try {
			Workbook workbook = null;
			String fileName = file.getName();
			if (fileName.endsWith("xlsx")) {
				workbook = new XSSFWorkbook();
			} else if (fileName.endsWith("xls")) {
				workbook = new HSSFWorkbook();
			} else {
				throw new RuntimeException("invalid file name, should be xls or xlsx");
			}
			Sheet sheet = workbook.createSheet(sheetTitle);
			int rowIndex = 0;
			// 创建顶部描述列
			Row descRow = sheet.createRow(rowIndex++);
			CellStyle style = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setFontName("黑体");
			font.setFontHeightInPoints((short) 11);
			font.setBold(true);
			style.setFont(font);
			for (int i = 0; i < columnDescList.size(); i++) {
				Cell cell = descRow.createCell(i);
				cell.setCellValue(columnDescList.get(i));
				cell.setCellStyle(style);
			}
			// 创建数据列
			for (int i = 0; i < dataList.size(); i++) {
				Row row = sheet.createRow(rowIndex++);
				for (int j = 0; j < dataList.get(i).size(); j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(dataList.get(i).get(j));
				}
			}
			// 将数据写入到excel文件中
			FileOutputStream fos = new FileOutputStream(file);
			workbook.write(fos);
			fos.close();
			workbook.close();
			logger.info(fileName + " written successfully");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("written execel failed!" + ex.getMessage());
		}
	}

}
