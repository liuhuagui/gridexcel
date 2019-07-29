package bean;

import java.io.Serializable;

/**
 *
 * databaseTable:payment
 */
public class Payment implements Serializable{
    /**
     *   支付id
     */
    private Long paymentId;

    /**
     *   交易订单号
     */
    private Long tradeOrderId;

    /**
     *   支付阶段
     */
    private Byte paymentPhase;

    /**
     *   支付权重，<100
     */
    private Byte paymentWeight;

    /**
     *   支付金额
     */
    private Double paymentAmount;

    /**
     *   支付方式，0微信，1支付宝，其他——银联
     */
    private String paymentWay;

    /**
     *   支付状态, 0未支付 1支付成功
     */
    private Byte paymentStatus;
    
    public Payment() {
	}

	public Payment(Long tradeOrderId) {
		super();
		this.tradeOrderId = tradeOrderId;
	}

	public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getTradeOrderId() {
        return tradeOrderId;
    }

    public void setTradeOrderId(Long tradeOrderId) {
        this.tradeOrderId = tradeOrderId;
    }

    public Byte getPaymentPhase() {
        return paymentPhase;
    }

    public void setPaymentPhase(Byte paymentPhase) {
        this.paymentPhase = paymentPhase;
    }

    public Byte getPaymentWeight() {
        return paymentWeight;
    }

    public void setPaymentWeight(Byte paymentWeight) {
        this.paymentWeight = paymentWeight;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public Byte getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Byte paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}