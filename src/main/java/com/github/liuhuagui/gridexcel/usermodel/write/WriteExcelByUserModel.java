package com.github.liuhuagui.gridexcel.usermodel.write;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Use user model to write excel file.<br>
 * userModel —— 
 * <ul>
 * <li>缺点：会将产生的spreadsheets对象整个保存在内存中，所以write Excel的大小受到堆内存（Heap space）大小限制。</li>
 * <li>优点：使用和理解更简单。</li>
 * <li>使用场景：可以写出数据量较小的Excel。</li>
 * </ul>
 * @author KaiKang
 * @since JDK1.8
 */
public class WriteExcelByUserModel<T> extends WriteExcel<T>{
	public WriteExcelByUserModel(Workbook workbook) {
	     super(workbook);
	}

	/**
	 * 使用{@link Workbook} 创建WriteExcel对象
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByUserModel<C> excel(Workbook workbook, Class<C> tClass) {
		return new WriteExcelByUserModel<C>(workbook);
	}

	/**
	 * 使用{@link XSSFWorkbook} 创建WriteExcel对象。
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByUserModel<C> excel(Class<C> tClass) {
		return excel(new XSSFWorkbook(), tClass);
	}
}