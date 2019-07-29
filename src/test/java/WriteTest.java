import bean.Payment;
import bean.TradeOrder;
import com.github.liuhuagui.gridexcel.GridExcel;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import util.MockData;
import util.PaymentWays;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.function.Function;

/**
 * 使用MockData从类路径下的序列化对象文件获取模拟数据List<String>
 * @author KaiKang
 */
public class WriteTest {

    private void putPaymentsFunction(LinkedHashMap<String, Function<TradeOrder, Object>> writeFunctionMap, final int index) {
        writeFunctionMap.put("第" + (1 + index) + "次支付订单号", to -> {
            Payment payment = to.getPayments().get(index);
            return payment.getPaymentId();
        });
        writeFunctionMap.put("第" + (1 + index) + "次支付金额", to -> {
            Payment payment = to.getPayments().get(index);
            return payment.getPaymentAmount();
        });
        writeFunctionMap.put("第" + (1 + index) + "次支付方式", to -> {
            Payment payment = to.getPayments().get(index);
            return PaymentWays.bankInstName(payment.getPaymentWay());
        });
        writeFunctionMap.put("第" + (1 + index) + "次支付状态", to -> {
            Payment payment = to.getPayments().get(index);
            return payment.getPaymentStatus() == 1 ? "支付完成" : "支付中";
        });

    }

    public LinkedHashMap<String,Function<TradeOrder,Object>> writeFunctionMap(){
        LinkedHashMap<String, Function<TradeOrder, Object>> writeFunctionMap = new LinkedHashMap<>();
        writeFunctionMap.put("订单号", to -> to.getTradeOrderId());
        writeFunctionMap.put("顾问编号", to -> to.getConsultantId());
        writeFunctionMap.put("顾问姓名", to -> to.getConsultant().getConsultantName());
        writeFunctionMap.put("顾问手机号", to -> to.getConsultant().getConsultantPhone());
        writeFunctionMap.put("客户编号", to -> to.getCustomerId());
        writeFunctionMap.put("客户名称", to -> to.getCustomer().getCustomerName());
        writeFunctionMap.put("客户手机号", to -> to.getCustomer().getCustomerPhone());
        writeFunctionMap.put("服务商编号", to -> to.getProviderId());
        writeFunctionMap.put("服务商名称", to -> to.getProvider().getProviderName());
        writeFunctionMap.put("服务商注册手机号", to -> to.getProvider().getLegalPersonPhone());
        writeFunctionMap.put("服务编号", to -> to.getServiceId());
        writeFunctionMap.put("服务名称", to -> to.getService().getServiceName());
        writeFunctionMap.put("单价", to -> to.getActualUnitPrice());
        writeFunctionMap.put("数量", to -> to.getServiceCount());
        writeFunctionMap.put("总额", to -> to.getActualSumPrice());
        writeFunctionMap.put("支付比例", to -> to.getPaymentRatio());
        writeFunctionMap.put("支付次数", to -> to.getPaymentTimes());
        writeFunctionMap.put("支付状态", to -> {
            Byte status = to.getPaymentStatus();
            return status == null ? "未支付" : ("第" + Math.abs(status) + "次支付" + (status > 0 ? "完成" : "中"));
        });
        // 为支付订单添加处理函数
        putPaymentsFunction(writeFunctionMap, 0);
        putPaymentsFunction(writeFunctionMap, 1);
        putPaymentsFunction(writeFunctionMap, 2);
        
        return writeFunctionMap;
    }


    /**
     * 使用UserModel写出数据到Excel
     * @throws Exception
     */
    @Test
    public void writeExcelByUserModel() throws Exception {
       GridExcel.writeByUserModel(TradeOrder.class)
                 .head(writeFunctionMap())//对象字段到Excel列的映射
                 .createSheet()
                 .process(MockData.data())//模拟数据。在这里设置业务数据集合。
                 .write(FileUtils.openOutputStream(new File("/excel/test.xlsx")));
    }

    /**
     * 使用Streaming UserModel写出数据到Excel
     * @throws Exception
     */
    @Test
    public void writeExcelByStreaming() throws Exception {
        GridExcel.writeByStreaming(TradeOrder.class)
                .head(writeFunctionMap())//对象字段到Excel列的映射
                .createSheet()
                .process(MockData.data())//模拟数据。在这里设置业务数据集合。
                .write(FileUtils.openOutputStream(new File("/excel/test.xlsx")));
    }
}
