package bean;

import java.io.Serializable;

/**
 *
 * databaseTable:service
 */
public class Service implements Serializable{

    /**
     *   服务ID
     */
    private String serviceId;

    /**
     *   服务名称
     */
    private String serviceName;

    /**
     *   服务图片路径
     */
    private String serviceImage;

    /**
     *   二级服务ID
     */
    private String secondServiceId;

    /**
     *   服务描述
     */
    private String serviceDescribe;

    /**
     *   单位价格
     */
    private String unitPrice;

	public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : (serviceId.trim().length()==0?null:serviceId);
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : (serviceName.trim().length()==0?null:serviceName);
    }

    public String getServiceImage() {
        return serviceImage;
    }

    public void setServiceImage(String serviceImage) {
        this.serviceImage = serviceImage == null ? null : (serviceImage.trim().length()==0?null:serviceImage);
    }

    public String getSecondServiceId() {
		return secondServiceId;
	}

	public void setSecondServiceId(String secondServiceId) {
		this.secondServiceId = secondServiceId;
	}

    public String getServiceDescribe() {
        return serviceDescribe;
    }

    public void setServiceDescribe(String serviceDescribe) {
        this.serviceDescribe = serviceDescribe == null ? null : (serviceDescribe.trim().length()==0?null:serviceDescribe);
    }

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

 
}