package com.github.liuhuagui.gridexcel.usermodel.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Function;

import com.github.liuhuagui.gridexcel.util.Assert;
import com.github.liuhuagui.gridexcel.util.ExcelType;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Use user model to read excel file.<br>
 * userModel ——
 * <ul>
 * <li>缺点：内存消耗大，会将excel信息全部加载到内存再进行处理。</li>
 * <li>优点：现成的API，使用和理解更简单。</li>
 * <li>使用场景：可以处理数据量较小的Excel。</li>
 * </ul>
 * @author KaiKang
 * @since JDK1.8
 */
public class ReadExcelByUserModel<T> extends ReadExcel<Row,T>{
	/**
	 * High level representation of a Excel workbook. 
	 */
	private Workbook workbook;
	
	public ReadExcelByUserModel(Workbook workbook) {
		super();
		this.workbook = workbook;
	}
	
	/**
	 * Use {@link Workbook} to create Excel object.
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> ReadExcelByUserModel<C> excel(Workbook workbook, Class<C> tClass) {
		return new ReadExcelByUserModel<C>(workbook);
	}
	
	/**
	 * 根据文件名与Excel输入流创建ReadExcel对象
	 * @param stream 输入流
	 * @param tClass 数据对象
	 * @param excelType Excel类型
	 * @return
	 * @throws IOException 
	 */
	public static <C> ReadExcelByUserModel<C> excel(InputStream stream, Class<C> tClass,ExcelType excelType) throws IOException {
		if(excelType == ExcelType.XLS)
			return excel(new HSSFWorkbook(stream), tClass);
		if(excelType == ExcelType.XLSX)
			return excel(new XSSFWorkbook(stream), tClass);
		throw new IllegalArgumentException("The GridExcel only supports .xls or .xlsx");
	}

	@Override
	public ReadExcelByUserModel<T> process(Function<Row, T> readFunction,int startNum) throws IOException {
		Assert.notNull(readFunction, "Parameter readFrunction can't be null.");
		if(Objects.isNull(list)){
			if(windowSize == 0)//不做大小控制
				list = new ArrayList<T>();
			else//使用窗口大小作为数组容量initialCapacity
				list = new ArrayList<T>(windowSize);
		}
		// Read the Sheet
		for (int numSheet = 0; numSheet < workbook.getNumberOfSheets(); numSheet++) {
			Sheet sheet = workbook.getSheetAt(numSheet);
			if (sheet == null)
				continue;
			// Read the Row
			for (int rowNum = startNum; rowNum <= sheet.getLastRowNum(); rowNum++) {
				Row row = sheet.getRow(rowNum);
				if (row == null) 
					continue;
				list.add(readFunction.apply(row));
				//调用监听函数，执行自定义逻辑
				invokeListener();
			}
		}
		workbook.close();
		return this;
	}
}