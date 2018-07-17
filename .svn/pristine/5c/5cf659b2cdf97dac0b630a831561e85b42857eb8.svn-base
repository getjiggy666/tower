package com.telecom.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelHelper2003 {

	private Workbook workbook;// 工作簿2003
	private int fileType = 0;

	public ExcelHelper2003(File file) {
		try {
			InputStream inp = new FileInputStream(file);
			
			if(! inp.markSupported()) {
				inp = new PushbackInputStream(inp, 8);
			}

			if(POIFSFileSystem.hasPOIFSHeader(inp)) {
				//System.out.println("2003及以下");
				workbook = new HSSFWorkbook(new FileInputStream(file));
				fileType = 0;
			}
			if(POIXMLDocument.hasOOXMLHeader(inp)) {
				//System.out.println("2007及以上");
				workbook = new XSSFWorkbook(new FileInputStream(file));
				fileType = 1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ExcelHelper2003(InputStream inp) {
		try {
			workbook = new HSSFWorkbook(inp);
			
			if(! inp.markSupported()) {
				inp = new PushbackInputStream(inp, 8);
			}

			if(POIFSFileSystem.hasPOIFSHeader(inp)) {
				//System.out.println("2003及以下");
				fileType = 0;
				
			}
			if(POIXMLDocument.hasOOXMLHeader(inp)) {
				//System.out.println("2007及以上");
				workbook = new XSSFWorkbook(inp);
				fileType = 1;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<List<String>> getResultList(){
		if(fileType == 0){
			return getDatasInSheet03(0);
		} else if(fileType == 1){
			return getDatasInSheet07(0);
		}
		return null;
	}
	
	//解析2003版本的excel
	public List<List<String>> getDatasInSheet03(int sheetNumber) {
		List<List<String>> result = new ArrayList<List<String>>();

		// 获得指定的sheet
		HSSFSheet sheet = (HSSFSheet)workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		
		//System.out.println(rowCount + " ####### rowCount:" + rowCount);

		// 遍历行row
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
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

	////解析2007版本的excel
	public List<List<String>> getDatasInSheet07(int sheetNumber) {
		List<List<String>> result = new ArrayList<List<String>>();

		// 获得指定的sheet
		XSSFSheet sheet = (XSSFSheet)workbook.getSheetAt(sheetNumber);
		// 获得sheet总行数
		int rowCount = sheet.getLastRowNum();
		
		//System.out.println(rowCount + " ####### rowCount:" + rowCount);

		// 遍历行row
		for (int rowIndex = 0; rowIndex <= rowCount; rowIndex++) {
			// 获得行对象
			XSSFRow row = sheet.getRow(rowIndex);
			if (null != row) {
				List<String> rowData = new ArrayList<String>();
				// 获得本行中单元格的个数
				int cellCount = row.getLastCellNum();
				// System.out.println(rowIndex+" ####### "+cellCount);
				int i = 0;
				// 遍历列cell
				for (short cellIndex = 0; cellIndex < cellCount; cellIndex++) {
					XSSFCell cell = row.getCell(cellIndex);
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
	
	private Object getCellString(Cell cell) {
		// TODO Auto-generated method stub
		Object result = null;
		if (cell != null) {
			cell.setCellType(HSSFCell.CELL_TYPE_STRING);
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
		return result;
	}

	public static void main(String[] args) {
		
		File file = new File("E:/a.xls");
		ExcelHelper2003 eh = new ExcelHelper2003(file);
		List<List<String>> list=new ArrayList<List<String>>();
		list=eh.getResultList();
		
		
		
		for (List ls : list) {
			System.out.println();
			for (int i = 0; i < ls.size(); i++) {
				System.out.print(ls.get(i) + "\t");
			}
		}
	}

}
