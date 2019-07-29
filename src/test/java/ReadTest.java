import bean.Consultant;
import bean.Payment;
import bean.TradeOrder;
import com.alibaba.fastjson.JSON;
import com.github.liuhuagui.gridexcel.GridExcel;
import com.github.liuhuagui.gridexcel.eventmodel.XLSEventModel;
import com.github.liuhuagui.gridexcel.eventmodel.XLSXEventModel;
import com.github.liuhuagui.gridexcel.util.ExcelType;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * 部分测试样例。<br>
 * 其中{@link TradeOrder}、{@link Consultant}、{@link bean.Provider}、{@link bean.Service}、{@link bean.Customer}、{@link Payment}
 * 是用来测试的业务对象，目的是展现出GridExcel对关联对象较好的支持能力。使用时可以参照以下样例。
 * @autor SunKaiKang
 * @date 2019/7/26 0026 13:10
 */
public class ReadTest {
    /**
     * 测试{@link XLSEventModel}。<br>
     * 业务逻辑处理方式二选一：
     * 1.放在readConsumer函数中。
     * 2.使用final or effective final的局部变量存放这写数据，做后续处理。
     */
    @Test
    public void xlsEventModelTest() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2003.xls");
        ArrayList<TradeOrder> arrayList = new ArrayList<TradeOrder>();
        XLSEventModel xls = new XLSEventModel(resourceAsStream,1, cs ->{
            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setTradeOrderId(Long.valueOf(cs.get(0)));
            Consultant consultant = new  Consultant();
            consultant.setConsultantName(cs.get(3));
            tradeOrder.setConsultant(consultant);
            tradeOrder.setPaymentRatio(cs.get(16));
            arrayList.add(tradeOrder);
        });
        xls.process();
        System.out.println(JSON.toJSONString(arrayList));
    }

    /**
     * 测试{@link XLSXEventModel}。<br>
     * 业务逻辑处理方式二选一：
     * 1.放在readConsumer函数中。
     * 2.使用final or effective final的局部变量存放这写数据，做后续处理。
     */
    @Test
    public void xlsxEventModelTest() throws IOException, OpenXML4JException, ParserConfigurationException, SAXException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2007.xlsx");
        ArrayList<TradeOrder> arrayList = new ArrayList<TradeOrder>();
        XLSXEventModel xlsx = new XLSXEventModel(resourceAsStream,1, cs ->{
            TradeOrder tradeOrder = new TradeOrder();
            tradeOrder.setTradeOrderId(Long.valueOf(cs.get(0)));
            Consultant consultant = new  Consultant();
            consultant.setConsultantName(cs.get(3));
            tradeOrder.setConsultant(consultant);
            tradeOrder.setPaymentRatio(cs.get(16));
            arrayList.add(tradeOrder);
        });
        xlsx.process();
        System.out.println(JSON.toJSONString(arrayList));
    }

    /**
     * 业务逻辑处理方式三选一：
     * 1.启用windowListener，并将业务逻辑放在该函数中。
     * 2.不启用windowListener，使用get()方法取回全部数据集合，做后续处理。
     * 3.readFunction函数，直接放在函数中处理 或 使用final or effective final的局部变量存放这写数据，做后续处理。
     * 注意：使用EventModel时readFunction函数的输入为每行的cell值集合List<String>。
     * @throws Exception
     */
    @Test
    public void readXlsByEventModel() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2003.xls");
        GridExcel.readByEventModel(resourceAsStream,TradeOrder.class, ExcelType.XLS)
                 .window(2,ts -> System.out.println(JSON.toJSONString(ts)))//推荐在这里执行自己的业务逻辑
                 .process(cs ->{
                     TradeOrder tradeOrder = new TradeOrder();
                     tradeOrder.setTradeOrderId(Long.valueOf(cs.get(0)));
                     Consultant consultant = new  Consultant();
                     consultant.setConsultantName(cs.get(3));
                     tradeOrder.setConsultant(consultant);
                     tradeOrder.setPaymentRatio(cs.get(16));
                     return tradeOrder;
                 },1);
    }

    /**
     * 业务逻辑处理方式三选一：
     * 1.启用windowListener，并将业务逻辑放在该函数中。
     * 2.不启用windowListener，使用get()方法取回全部数据集合，做后续处理。
     * 3.readFunction函数，直接放在函数中处理 或 使用final or effective final的局部变量存放这写数据，做后续处理。
     * 注意：使用EventModel时readFunction函数的输入为每行的cell值集合List<String>。
     * @throws Exception
     */
    @Test
    public void readXlsxByEventModel() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2007.xlsx");
        GridExcel.readByEventModel(resourceAsStream,TradeOrder.class,ExcelType.XLSX)
                .window(2,ts -> System.out.println(JSON.toJSONString(ts)))//推荐在这里执行自己的业务逻辑
                .process(cs ->{
                    TradeOrder tradeOrder = new TradeOrder();
                    tradeOrder.setTradeOrderId(Long.valueOf(cs.get(0)));
                    Consultant consultant = new  Consultant();
                    consultant.setConsultantName(cs.get(3));
                    tradeOrder.setConsultant(consultant);
                    tradeOrder.setPaymentRatio(cs.get(16));
                    return tradeOrder;
                },1);
    }

    /**
     * 业务逻辑处理方式三选一：
     * 1.启用windowListener，并将业务逻辑放在该函数中。
     * 2.不启用windowListener，使用get()方法取回全部数据集合，做后续处理。
     * 3.readFunction函数，直接放在函数中处理 或 使用final or effective final的局部变量存放这写数据，做后续处理。
     * 注意：使用UserModel时readFunction函数的输入为为Row对象。
     * @throws Exception
     */
    @Test
    public void readXlsByUserModel() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2003.xls");
        GridExcel.readByUserModel(resourceAsStream,TradeOrder.class, ExcelType.XLS)
                .window(2,ts -> System.out.println(JSON.toJSONString(ts)))//推荐在这里执行自己的业务逻辑
                .process(row ->{
                    TradeOrder tradeOrder = new TradeOrder();
                    tradeOrder.setTradeOrderId(Long.valueOf(row.getCell(0).getStringCellValue()));
                    Consultant consultant = new  Consultant();
                    consultant.setConsultantName(row.getCell(3).getStringCellValue());
                    tradeOrder.setConsultant(consultant);
                    tradeOrder.setPaymentRatio(row.getCell(16).getStringCellValue());
                    return tradeOrder;
                },1);
    }

    /**
     * 业务逻辑处理方式三选一：
     * 1.启用windowListener，并将业务逻辑放在该函数中。
     * 2.不启用windowListener，使用get()方法取回全部数据集合，做后续处理。
     * 3.readFunction函数，直接放在函数中处理 或 使用final or effective final的局部变量存放这写数据，做后续处理。
     * 注意：使用UserModel时readFunction函数的输入为为Row对象。
     * @throws Exception
     */
    @Test
    public void readXlsxByUserModel() throws Exception {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("2007.xlsx");
        GridExcel.readByUserModel(resourceAsStream,TradeOrder.class,ExcelType.XLSX)
                .window(2,ts -> System.out.println(JSON.toJSONString(ts)))//推荐在这里执行自己的业务逻辑
                .process(row ->{
                    TradeOrder tradeOrder = new TradeOrder();
                    tradeOrder.setTradeOrderId(Long.valueOf(row.getCell(0).getStringCellValue()));
                    Consultant consultant = new  Consultant();
                    consultant.setConsultantName(row.getCell(3).getStringCellValue());
                    tradeOrder.setConsultant(consultant);
                    tradeOrder.setPaymentRatio(row.getCell(16).getStringCellValue());
                    return tradeOrder;
                },1);
    }

}
