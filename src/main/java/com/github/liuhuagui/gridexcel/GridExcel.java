package com.github.liuhuagui.gridexcel;

import com.github.liuhuagui.gridexcel.eventmodel.EventModel;
import com.github.liuhuagui.gridexcel.usermodel.read.ReadExcelByEventModel;
import com.github.liuhuagui.gridexcel.usermodel.read.ReadExcelByUserModel;
import com.github.liuhuagui.gridexcel.usermodel.write.WriteExcelByStreaming;
import com.github.liuhuagui.gridexcel.usermodel.write.WriteExcelByUserModel;
import com.github.liuhuagui.gridexcel.util.ExcelType;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

/**
 * 统一标记。基于函数编程来实现，算法设计上类似于CSS中的Grid布局（先设置好布局再放置元素），所以叫做“GridExcel”。<br>
 * @author KaiKang
 */
public class GridExcel {
	/**
	 * Use {@link Workbook} to create Excel object.
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> ReadExcelByUserModel<C> readByUserModel(Workbook workbook, Class<C> tClass) {
		return ReadExcelByUserModel.excel(workbook, tClass);
	}
	
	/**
	 * 根据文件名与Excel输入流创建ReadExcel对象
	 * @param stream 输入流
	 * @param tClass 数据对象
	 * @param excelType Excel类型
	 * @return
	 * @throws IOException 
	 */
	public static <C> ReadExcelByUserModel<C> readByUserModel(InputStream stream, Class<C> tClass, ExcelType excelType) throws IOException {
		return ReadExcelByUserModel.excel(stream, tClass, excelType);
	}
	
	/**
	 * Use {@link EventModel} to create Excel object.
	 * @param eventModel
	 * @param tClass
	 * @return
	 */
	public static <C> ReadExcelByEventModel<C> readByEventModel(EventModel eventModel, Class<C> tClass) {
		return ReadExcelByEventModel.excel(eventModel, tClass);
	}
	
	/**
	 * 根据文件名与Excel输入流创建ReadExcel对象
	 * @param stream 输入流
	 * @param tClass 数据对象
	 * @param excelType Excel类型
	 * @return
	 * @throws IOException 
	 * @throws OpenXML4JException 
	 */
	public static <C> ReadExcelByEventModel<C> readByEventModel(InputStream stream, Class<C> tClass,ExcelType excelType) throws IOException, OpenXML4JException {
		return ReadExcelByEventModel.excel(stream, tClass, excelType);
	}
	
	/**
	 * 使用{@link Workbook} 创建Excel对象
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByUserModel<C> writeByUserModel(Workbook workbook, Class<C> tClass) {
		return WriteExcelByUserModel.excel(workbook, tClass);
	}

	/**
	 * 使用{@link XSSFWorkbook} 创建WriteExcel对象。
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByUserModel<C> writeByUserModel(Class<C> tClass) {
		return WriteExcelByUserModel.excel(tClass);
	}
	
	/**
	 * 使用{@link Workbook} 创建WriteExcel对象
	 * @param workbook
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> writeByStreaming(Workbook workbook, Class<C> tClass) {
		return WriteExcelByStreaming.excel(workbook, tClass);
	}

	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> writeByStreaming(Class<C> tClass) {
		return WriteExcelByStreaming.excel(tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param rowAccessWindowSize the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> writeByStreaming(int rowAccessWindowSize, Class<C> tClass) {
		return WriteExcelByStreaming.excel(rowAccessWindowSize, tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param workbook the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> writeByStreaming(XSSFWorkbook workbook, Class<C> tClass) {
		return WriteExcelByStreaming.excel(workbook, tClass);
	}
	
	/**
	 * 使用{@link SXSSFWorkbook} 创建WriteExcel对象。使用Streaming Usermodel API去Write出数据到Excel，降低内存占用。
	 * @param workbook
	 * @param rowAccessWindowSize the number of rows that are kept in memory until flushed out, see {@link SXSSFWorkbook}.
	 * @param tClass
	 * @return
	 */
	public static <C> WriteExcelByStreaming<C> writeByStreaming(XSSFWorkbook workbook, int rowAccessWindowSize, Class<C> tClass) {
		return WriteExcelByStreaming.excel(workbook, rowAccessWindowSize, tClass);
	}
}
