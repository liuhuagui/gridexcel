package bean;

import java.io.Serializable;
import java.util.List;

/**
 *
 * databaseTable:trade_order
 */
public class TradeOrder implements Serializable{

    /**
     *   交易订单号
     */
    private Long tradeOrderId;

    /**
     *   交易类型
     */
    private Byte tradeType;

    /**
     *   支付比例
     */
    private String paymentRatio;

    /**
     *   支付次数
     */
    private Byte paymentTimes;

    /**
     *   服务ID
     */
    private Long serviceId;

    /**
     *   服务商Id
     */
    private String providerId;

    /**
     *   客户ID
     */
    private String customerId;

    /**
     *   顾问ID
     */
    private String consultantId;

    /**
     *   标准单位价格
     */
    private Double standardUnitPrice;

    /**
     *   实际单位价格
     */
    private Double actualUnitPrice;

    /**
     *   服务数量
     */
    private Integer serviceCount;

    /**
     *   标准总价
     */
    private Double standardSumPrice;

    /**
     *   实际总价
     */
    private Double actualSumPrice;

    /**
     *   提前提醒时间
     */
    private String advancedRemindTime;

    /**
     *   强制更新流程时间
     */
    private String mandatoryUpdateTime;

    /**
     *   当前流程序号
     */
    private Integer currentProcessNum;

    
    /**
     *  交易地址
     */
    private String tradeAddress;

    /**
     *   n(|n|∈N+) 第|n|次支付，<0支付中，>0支付完成
     */
    private Byte paymentStatus;
    /**
     *   关联集合的属性
     */
    private List<Payment> payments;
    
    private Customer customer;
    
    private Provider provider;
    
    private Consultant consultant;
    
    private Service service;

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Provider getProvider() {
		return provider;
	}

	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public Consultant getConsultant() {
		return consultant;
	}

	public void setConsultant(Consultant consultant) {
		this.consultant = consultant;
	}

	public Long getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(Long tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public Byte getTradeType() {
        return tradeType;
    }

    public void setTradeType(Byte tradeType) {
        this.tradeType = tradeType;
    }

    public String getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(String paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public Byte getPaymentTimes() {
        return paymentTimes;
    }

    public void setPaymentTimes(Byte paymentTimes) {
        this.paymentTimes = paymentTimes;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public Double getStandardUnitPrice() {
        return standardUnitPrice;
    }

    public void setStandardUnitPrice(Double standardUnitPrice) {
        this.standardUnitPrice = standardUnitPrice;
    }

    public Double getActualUnitPrice() {
        return actualUnitPrice;
    }

    public void setActualUnitPrice(Double actualUnitPrice) {
        this.actualUnitPrice = actualUnitPrice;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public Double getStandardSumPrice() {
        return standardSumPrice;
    }

    public void setStandardSumPrice(Double standardSumPrice) {
        this.standardSumPrice = standardSumPrice;
    }

    public Double getActualSumPrice() {
        return actualSumPrice;
    }

    public void setActualSumPrice(Double actualSumPrice) {
        this.actualSumPrice = actualSumPrice;
    }

    public String getAdvancedRemindTime() {
        return advancedRemindTime;
    }

    public void setAdvancedRemindTime(String advancedRemindTime) {
        this.advancedRemindTime = advancedRemindTime;
    }

    public String getMandatoryUpdateTime() {
        return mandatoryUpdateTime;
    }

    public void setMandatoryUpdateTime(String mandatoryUpdateTime) {
        this.mandatoryUpdateTime = mandatoryUpdateTime;
    }

    public Integer getCurrentProcessNum() {
        return currentProcessNum;
    }

    public void setCurrentProcessNum(Integer currentProcessNum) {
        this.currentProcessNum = currentProcessNum;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
}