package util;

import bean.*;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 生成模拟数据
 * @author KaiKang
 */
public class MockData {
    /**
     * 生成模拟数据
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void init() throws IOException, ClassNotFoundException {
        TradeOrder tradeOrder = new TradeOrder();
        tradeOrder.setTradeOrderId(1l);
        tradeOrder.setCustomerId("2123122");
        tradeOrder.setConsultantId("1222");
        tradeOrder.setServiceId(11l);
        tradeOrder.setProviderId("233");
        tradeOrder.setActualUnitPrice(2312.0);
        tradeOrder.setServiceCount(2);
        tradeOrder.setActualSumPrice(4624.0);
        tradeOrder.setPaymentRatio("1:3:4");
        tradeOrder.setPaymentTimes((byte) 2);
        tradeOrder.setPaymentStatus((byte) -2);

        Customer customer = new Customer();
        customer.setCustomerId("2123122");
        customer.setCustomerName("测试客户");
        customer.setCustomerPhone("2312331231");
        tradeOrder.setCustomer(customer);

        Consultant consultant = new Consultant();
        consultant.setConsultantId("1222");
        consultant.setConsultantPhone("23131221132");
        consultant.setConsultantName("KaiKang");
        tradeOrder.setConsultant(consultant);

        Provider provider = new Provider();
        provider.setProviderId("233");
        provider.setProviderName("测试服务商");
        provider.setLegalPersonPhone("2131332123");
        tradeOrder.setProvider(provider);

        Service service = new Service();
        service.setServiceName("测试服务");
        tradeOrder.setService(service);

        ArrayList<Payment> payments = new ArrayList<>();
        Payment e = new Payment();
        e.setPaymentWay("1");
        e.setPaymentId(1111l);
        e.setPaymentAmount(12.0);
        e.setPaymentStatus((byte) 1);
        payments.add(e);
        Payment e1 = new Payment();
        e1.setPaymentWay("0");
        e1.setPaymentId(1111l);
        e1.setPaymentAmount(12.0);
        e1.setPaymentStatus((byte) 0);
        payments.add(e1);
        tradeOrder.setPayments(payments);

        List<TradeOrder> tradeOrders = new ArrayList<>();
        for(int i=0 ;i<200;i++){
            tradeOrders.add(tradeOrder);
        }
        System.out.println(tradeOrders.size());

        File file = new File("/excel/datasource.obj");
        FileOutputStream fileOutputStream = FileUtils.openOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(tradeOrders);
        objectOutputStream.flush();
    }

    /**
     * 从类路径中获取模拟数据
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static List<TradeOrder> data() throws IOException, ClassNotFoundException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("datasource.obj");
        ObjectInputStream objectInputStream = new ObjectInputStream(resourceAsStream);
        return (List<TradeOrder>) objectInputStream.readObject();
    }

}
