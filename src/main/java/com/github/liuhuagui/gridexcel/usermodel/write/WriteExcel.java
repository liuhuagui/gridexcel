package com.github.liuhuagui.gridexcel.usermodel.write;

import com.github.liuhuagui.gridexcel.util.Assert;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Write excel file.<br>
 * @author KaiKang
 * @since JDK1.8
 */
public abstract class WriteExcel<T>{
	private static Logger log = LoggerFactory.getLogger(WriteExcel.class);
	/**实际的Excel对象*/
	private Workbook workbook;
	/**当前正在处理的Sheet*/
	private Sheet currentSheet;
	/**代替null与异常填充到单元格的符号*/
	private String fill;
	
	/**是否拥有首行*/
	private boolean hasHead;
	/**对于整个workbook中所有Sheet默认的首行样式*/
	private CellStyle defaultHeadStyle;
	/**Sheet和首行样式的映射*/
	private Map<Sheet, CellStyle> headStyles;

	/**将数据集合写出到Excel，每列数据对应的写出方式（函数接口，对象字段到Excel列的映射），为了保证处理顺序，使用{@link LinkedHashMap}*/
	private LinkedHashMap<String, Function<T, Object>> writeFunctionMap;

	public WriteExcel(Workbook workbook) {
		this.workbook = workbook;
		this.hasHead = false;//默认没有首行
		this.headStyles = new HashMap<Sheet, CellStyle>();
		this.fill = " - ";
	}

	/**
	 * 设置填充信息，默认为“ - ”
	 * @param fill
	 * @return
	 */
	public WriteExcel<T> fill(String fill) {
		this.fill = fill;
		return this;
	}
	
	/**
	 * 创建一个新的Sheet，sheetName默认为Sheet+ sheets.size()
	 * @return
	 */
	public WriteExcel<T> createSheet() {
		this.currentSheet = workbook.createSheet();
		return this;
	}

	/**创一个名为sheetName的Sheet
	 * @param sheetName
	 * @return
	 */
	public WriteExcel<T> createSheet(String sheetName) {
		this.currentSheet = workbook.createSheet(sheetName);
		return this;
	}

	/**
	 * 复制索引为sheetIndex的sheet（新的sheet）
	 * @param sheetIndex
	 * @return
	 */
	public WriteExcel<T> cloneSheet(int sheetIndex) {
		this.currentSheet = workbook.cloneSheet(sheetIndex);
		return this;
	}

	/**
	 * 取出索引为sheetIndex的sheet作为当前sheet
	 * @param sheetIndex
	 * @return
	 */
	public WriteExcel<T> sheet(int sheetIndex) {
		this.currentSheet = workbook.getSheetAt(sheetIndex);
		return this;
	}

	/**
	 * 取出名为sheetName的sheet作为当前sheet
	 * @param sheetName
	 * @return
	 */
	public WriteExcel<T> sheet(String sheetName) {
		this.currentSheet = workbook.getSheet(sheetName);
		return this;
	}

	/**
	 * 为当前sheet设置首行样式headStyle
	 * @param styleFunction 一个函数接口，输入Workbook，返回自定义CellStyle
	 * @return
	 */
	public WriteExcel<T> headStyle(Function<Workbook, CellStyle> styleFunction) {
		headStyles.put(currentSheet, styleFunction.apply(workbook));
		return this;
	}

	/**
	 * 没有首行的解析方式。此时writeFunctionMap的Key可为任意不重复的值，建议设置为阿拉伯数字。
	 * <br><b>为了保证处理顺序，使用{@link LinkedHashMap}</b>
	 * @param writeFunctionMap 对象字段到Excel列的映射
	 * @return
	 */
	public WriteExcel<T> noHead(LinkedHashMap<String, Function<T, Object>> writeFunctionMap) {
		this.hasHead = false;
		this.writeFunctionMap = writeFunctionMap;
		return this;
	}

	/**
	 * 拥有首行的解析方式。此时writeFunctionMap的Key会用作首行的列名
	 * <br><b>为了保证处理顺序，使用{@link LinkedHashMap}</b>
	 * @param writeFunctionMap 对象字段到Excel列的映射
	 * @return
	 */
	public WriteExcel<T> head(LinkedHashMap<String, Function<T, Object>> writeFunctionMap) {
		this.hasHead = true;
		this.writeFunctionMap = writeFunctionMap;
		return this;
	}
	
	/**
	 * 将数据集合写出到Excel
	 * @return
	 */
	public WriteExcel<T> process(List<T> list) {
		Assert.notEmpty(list,"The list is null or empty.");
		Assert.notNull(currentSheet, "The currentSheet is null");
		//用来创建行，如果已有首行，j=1
		int j = 0;
		//创建首行
		if(hasHead){
			Row headRow = currentSheet.createRow(0);
			CellStyle headStyle = headCellStyle();
			//创建首行
			int i = 0;
			for(String name : writeFunctionMap.keySet()){
			    Cell headCell = headRow.createCell(i++);
			    headCell.setCellValue(name);
			    headCell.setCellStyle(headStyle);
			}
			j = 1;//如果已有首行，j=1
		}
		
		//创建其余行
		for(T t : list){
			Row createRow = currentSheet.createRow(j++);
			int i = 0;
			for(Function<T, Object> function : writeFunctionMap.values()){
				createRow.createCell(i++).setCellValue(fillCellValue(function, t));
			}
		}
		
		return this;
	}

	/**
	 * 写出Excel到某个数据流
	 * @param stream
	 * @throws IOException
	 */
	public void write(OutputStream stream) throws IOException {
		this.workbook.write(stream);
	}

	/**
	 * 将Excel写出至字节数组
	 * @return
	 * @throws IOException
	 */
	public byte[] writeBytes() throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		workbook.write(os);
		workbook.close();
		return os.toByteArray();
	}

	/**
	 * 创建表格头Cell样式。可由子类复写
	 * @return
	 */
	protected CellStyle headCellStyle() {
		//如果自定义了，返回自定义的cellStyle
		CellStyle cellStyle0 = headStyles.get(currentSheet);
		if(cellStyle0 != null)
			return cellStyle0;
		
		//如果没有自定义，则返回默认样式
		if(defaultHeadStyle != null)
			return defaultHeadStyle;
		
		//如果也没有默认样式，创建默认样式，并存下来允许所有Sheet使用
		CellStyle cellStyle = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setBold(true);
		cellStyle.setFont(font);

		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBottomBorderColor((short) 0x000000);
		cellStyle.setTopBorderColor((short) 0x000000);
		cellStyle.setLeftBorderColor((short) 0x000000);
		cellStyle.setRightBorderColor((short) 0x000000);
		
		this.defaultHeadStyle = cellStyle;
		return cellStyle;
	}

	/**
	 * 如果函数接口function，调用返回值为null或抛出异常，则使用fill符号来填充表格Cell。可由子类复写
	 * @param function 函数接口
	 * @param t 数据对象
	 * @return 填充符号
	 */
	protected String fillCellValue(Function<T,Object> function,T t) {
		try {
			Object apply = function.apply(t);
			if(apply != null)
			   return String.valueOf(apply);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return fill;
	}
}