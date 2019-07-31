package com.github.liuhuagui.gridexcel.usermodel.read;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

import com.github.liuhuagui.gridexcel.eventmodel.EventModel;
import com.github.liuhuagui.gridexcel.eventmodel.XLSEventModel;
import com.github.liuhuagui.gridexcel.eventmodel.XLSXEventModel;
import com.github.liuhuagui.gridexcel.util.Assert;
import com.github.liuhuagui.gridexcel.util.ExcelType;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;

/**
 * Use event model to read excel file.<br>
 * eventModel —— 
 * <ul>
 * <li>缺点：没有现成的API，使用和理解较为复杂，适合中高级程序员（GridExcel的目标之一就是让EventModel的使用变得简单）</li>
 * <li>优点：非常小的内存占用，并没有在一开始就将所有内容加载到内存中，而是把主体内容的处理（存储，使用，丢弃）都交给了用户，用户可以自定义监听函数来处理这些内容。</li>
 * <li>使用场景：可以处理较大数据量的Excel，避免OOM和频繁FullGC</li>
 * </ul>
 * @author KaiKang
 * @since JDK1.8
 */
public class ReadExcelByEventModel<T> extends ReadExcel<List<String>,T>{
	/**
	 * The model of using POI Event API to read excel.
	 */
	private EventModel eventModel;

	public ReadExcelByEventModel(EventModel eventModel) {
		super();
		this.eventModel = eventModel;
	}
	
	/**
	 * Use {@link EventModel} to create Excel object.
	 * @param eventModel
	 * @param tClass
	 * @return
	 */
	public static <C> ReadExcelByEventModel<C> excel(EventModel eventModel, Class<C> tClass) {
		return new ReadExcelByEventModel<C>(eventModel);
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
	public static <C> ReadExcelByEventModel<C> excel(InputStream stream, Class<C> tClass, ExcelType excelType) throws IOException, OpenXML4JException {
		if(excelType == ExcelType.XLS)
			return excel(new XLSEventModel(stream), tClass);
		if(excelType == ExcelType.XLSX)
			return excel(new XLSXEventModel(stream), tClass);
		throw new IllegalArgumentException("The GridExcel only supports .xls or .xlsx");
	}

	@Override
	public ReadExcelByEventModel<T> process(Function<List<String>,T> readFunction,int startNum) throws Exception {
		Assert.notNull(readFunction, "Parameter readFrunction can't be null.");
		if(Objects.isNull(list)){
			if(windowSize == 0)//不做大小控制
				list = new ArrayList<T>();
			else//使用窗口大小作为数组容量initialCapacity
				list = new ArrayList<T>(windowSize);
		}
		//每读完一行记录（one row of records），调用该函数
		Consumer<List<String>> readConsumer = cells ->{
			list.add(readFunction.apply(cells));
			//调用监听函数，执行自定义逻辑
			invokeListener();
		};
		eventModel.startRow(startNum)
		          .readConsumer(readConsumer)
		          .process();
		return this;
	}
}