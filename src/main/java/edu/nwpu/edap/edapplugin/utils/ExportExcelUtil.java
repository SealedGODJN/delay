package edu.nwpu.edap.edapplugin.utils;

import com.alibaba.fastjson.JSONObject;
import edu.nwpu.edap.edapplugin.bean.Report;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExportExcelUtil {

	public static void exportExcel(Class clazz,List<Report> list, String pathName, String fileName) {

		if (pathName == null) {
			System.err.println("未输入导出文件路径的名称，默认指定为H盘");
			pathName = "E:\\";
		}
		if (fileName == null) {
			System.err.println("未输入导出文件的名称，默认指定为端到端延迟分析数据");
			fileName = "端到端延迟分析数据";
		}
		String outputFile = pathName + fileName + ".xlsx";
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Sheet1");
		XSSFRow row = sheet.createRow(0);
		// 给单元格设置样式
		CellStyle cellStyle = wb.createCellStyle();
		// 设置字体样式
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 12);
		font.setBold(true);
		cellStyle.setFont(font);
		// 设置单元格的背景颜色
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		// 设置单元格填充样式
//        cellStyle.setFillPattern(IndexedColors.WHITE.getIndex());

		List<String> strFields = new ArrayList<>();

		List<JSONObject> jsonObjects = beanToJson(list);
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			XSSFCell cell = row.createCell(i);
			cell.setCellType(CellType.STRING);
			cell.setCellValue(fields[i].getName());
			strFields.add(fields[i].getName());

			// cell.setCellStyle(cellStyle);
		}

		int total = jsonObjects.size();
		for (int i = 1; i <= jsonObjects.size(); i++) {
			if (total >= 100 && i % (total / 100) == 0) {
				System.out.println("程序已导出：" + i / (total / 100));
			}
			XSSFRow dataRow = sheet.createRow(i);
			for (int j = 0; j < strFields.size(); j++) {
				XSSFCell dataCell = dataRow.createCell(j);
				dataCell.setCellType(CellType.STRING);
				dataCell.setCellValue(jsonObjects.get(i - 1).get(strFields.get(j)).toString());
			}
		}

		try {
			FileOutputStream fos = new FileOutputStream(outputFile);
			wb.write(fos);
			fos.flush();
			fos.close();
			wb.close();
			System.out.println("文件生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 将JavaBean转化为JSON对象
	private static List<JSONObject> beanToJson(List dataList) {
		List<JSONObject> jsonObjects = new ArrayList<>();
		dataList.forEach(list -> {
			JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(list));
			jsonObjects.add(jsonObject);
		});
		return jsonObjects;
	}
}
