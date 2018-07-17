package com.telecom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelHelper {
	private HSSFWorkbook workbook;// 工作簿

	public ExcelHelper(File file) {
		try {
			new ExcelHelper(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ExcelHelper(InputStream in) {
		try {
			// 获取工作薄workbook
			workbook = new HSSFWorkbook(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<List> getDatasInSheet(int sheetNumber) {
		List<List> result = new ArrayList<List>();

		// 获得指定的sheet
		HSSFSheet sheet = workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		if (rowCount < 1) {
			return result;
		}
		System.out.println(rowCount + " ####### rowCount:" + rowCount);

		// 遍历行row
		for (int rowIndex = 1; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			HSSFRow row = sheet.getRow(rowIndex);
			if (null != row) {
				List<String> rowData = new ArrayList<String>();
				// 获得本行中单元格的个数
				int cellCount = row.getLastCellNum();
				// System.out.println(rowIndex+" ####### "+cellCount);
				int i = 0;
				// 遍历列cell
				for (short cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					HSSFCell cell = row.getCell(cellIndex);
					// System.out.println("@@@@@@@@@ "+cellIndex);
					// 获得指定单元格中的数据
					Object cellStr = this.getCellString(cell);
					if (cellStr == null) {
						i++;
					}
					rowData.add(cellStr == null ? "" : cellStr.toString());
				}
				if (rowData.size() > 0 && i < cellCount) {
					result.add(rowData);
				} else {
					// break;
				}
			}
		}

		return result;
	}

	private Object getCellString(HSSFCell cell) {
		// TODO Auto-generated method stub
		Object result = null;
		if (cell != null) {
			// 单元格类型：Numeric:0,String:1,Formula:2,Blank:3,Boolean:4,Error:5
			int cellType = cell.getCellType();
			switch (cellType) {
			case HSSFCell.CELL_TYPE_STRING:
				result = cell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:
				result = cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				result = cell.getNumericCellValue();
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				result = cell.getBooleanCellValue();
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				result = null;
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				result = null;
				break;
			default:
				System.out.println("枚举了所有类型");
				break;
			}
		}

		if (result != null && result.getClass().equals(Double.class)) {
			Double d = (Double) result;
			result = d.intValue();
		}

		return result;
	}
	
	/**
	 * 复制图片文件至图片目录
	 * @param imageFile 图片文件
	 * @return 图片文件路径    
	 */
	public static String copyExcelFile(ServletContext servletContext, File excelFile) {
		if (excelFile == null) {
			return null;
		}
		String formatName = "xls";
		if (formatName == null) {
			throw new IllegalArgumentException("excelFile format error!");
		}
		String destImagePath = SettingUtil.getSetting().getImageUploadRealPath() + "/" + CommonUtil.getUUID() + "." + formatName;
		File destImageFile = new File(servletContext.getRealPath(destImagePath));
		File destImageParentFile = destImageFile.getParentFile();
		if (!destImageParentFile.isDirectory()) {
			destImageParentFile.mkdirs();
		}
		try {
			FileUtils.copyFile(excelFile, destImageFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destImagePath;
	}

	public static void main(String[] args) {
		
		File file = new File("hd.xlsx");
		ExcelHelper eh = new ExcelHelper(file);
		List<List> list = eh.getDatasInSheet(0);
		for (List ls : list) {
			System.out.println();
			for (int i = 0; i < ls.size(); i++) {
				System.out.print(ls.get(i) + "\t");
			}
		}
	}

}
