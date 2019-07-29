package com.github.liuhuagui.gridexcel.usermodel.write;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Use API-compatible streaming extension of XSSF to write very large excel file.<br>
 * streaming userModel—— 
 * <ul>
 * <li>缺点：<ol>
 * <li>仅支持XSSF；</li>
 * <li>Sheet.clone() is not supported；</li>
 * <li>Formula evaluation is not supported；</li>
 * <li>Only a limited number of rows are accessible at a point in time.</li>
 * </ol></li>
 * <li>优点：通过滑动窗口来实现，内存中只保留指定size of rows的内容，超出部分被写出到临时文件，write Excel的大小不再受到堆内存（Heap space）大小限制。</li>
 * <li>使用场景：可以写出非常大的Excel。</li>
 * </ul>
 * @author KaiKang
 * @since JDK1.8
 */
public class WriteExcelByStreaming<T> extends WriteExcel<T>{
	public WriteExcelByStreaming(Workbook workbook) {
	     super(workbook);
	}

	/**
	 * 使用{@link Workbook} 创建WriteExcel对象
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> excel(Workbook workbook, Class<C> tClass) {
		return new WriteExcelByStreaming<C>(workbook);
	}

	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> excel(Class<C> tClass) {
		return excel(new SXSSFWorkbook(), tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param rowAccessWindowSize the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> excel(int rowAccessWindowSize, Class<C> tClass) {
		return excel(new SXSSFWorkbook(rowAccessWindowSize), tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param workbook the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> excel(XSSFWorkbook workbook, Class<C> tClass) {
		return excel(new SXSSFWorkbook(workbook), tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param workbook
	 * @param rowAccessWindowSize the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> excel(XSSFWorkbook workbook, int rowAccessWindowSize, Class<C> tClass) {
		return excel(new SXSSFWorkbook(workbook,rowAccessWindowSize), tClass);
	}
	
	/**
	 * Sheet.clone() is not supported in Streaming userModel.
	 */
	@Override
	public WriteExcel<T> cloneSheet(int sheetIndex){
		throw new IllegalArgumentException("Sheet.clone() is not supported in Streaming userModel.");
	}
}