package bean;

import java.io.Serializable;

/**
 *
 * databaseTable:customer
 */
public class Customer implements Serializable {
	/**
     *   客户ID
     */
    private String customerId;

    /**
     *   客户联系方式
     */
    private String customerPhone;

    /**
     *   密码
     */
    private String passWord;
    
    /**
     *   新密码
     */
    private String newPassWord;

    /**
     *   验证码
     */
    private String validateNum;

    /**
     *   失效时间
     */
    private String validateTime;
    
    /**
     *  冻结时间
     */
    private String frozenTime;
    
    /**
     *  解冻时间
     */
    private String thawingTime;

    /**
     *   昵称
     */
    private String customerName;

    /**
     *   头像路径
     */
    private String customerImage;

    /**
     *   客户类型
     */
    private String customerType;

    /**
     *   邮箱
     */
    private String customerEmail;

    /**
     *   职业
     */
    private String customerProfession;

    /**
     *   城市
     */
    private String customerCity;

    /**
     *   了解渠道
     */
    private String knowWay;
    
    /**
     * 未读通知数
     */
    private Integer unreadNotificationCount;
    
    /**
     * 已读通知数
     */
    private Integer readNotificationCount;
    
    /**
     * 关注的顾问总数
     */
    private Integer toConsultantCount;
    
    public String findKey() {
        return customerId;
    }

	public Integer getToConsultantCount() {
		return toConsultantCount;
	}

	public void setToConsultantCount(Integer toConsultantCount) {
		this.toConsultantCount = toConsultantCount;
	}

	public Integer getUnreadNotificationCount() {
		return unreadNotificationCount;
	}

	public void setUnreadNotificationCount(Integer unreadNotificationCount) {
		this.unreadNotificationCount = unreadNotificationCount;
	}

	public Integer getReadNotificationCount() {
		return readNotificationCount;
	}

	public void setReadNotificationCount(Integer readNotificationCount) {
		this.readNotificationCount = readNotificationCount;
	}

	public String getFrozenTime() {
		return frozenTime;
	}

	public void setFrozenTime(String frozenTime) {
		this.frozenTime = frozenTime;
	}

	public String getThawingTime() {
		return thawingTime;
	}

	public void setThawingTime(String thawingTime) {
		this.thawingTime = thawingTime;
	}

	public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : (customerId.trim().length()==0?null:customerId);
    }
    
    public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone == null ? null : (customerPhone.trim().length()==0?null:customerPhone);
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : (passWord.trim().length()==0?null:passWord);
    }

    public String getValidateNum() {
        return validateNum;
    }

    public void setValidateNum(String validateNum) {
        this.validateNum = validateNum == null ? null : (validateNum.trim().length()==0?null:validateNum);
    }

    public String getValidateTime() {
        return validateTime;
    }

    public void setValidateTime(String validateTime) {
        this.validateTime = validateTime == null ? null : (validateTime.trim().length()==0?null:validateTime);
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : (customerName.trim().length()==0?null:customerName);
    }

    public String getCustomerImage() {
        return customerImage;
    }

    public void setCustomerImage(String customerImage) {
        this.customerImage = customerImage == null ? null : (customerImage.trim().length()==0?null:customerImage);
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType == null ? null : (customerType.trim().length()==0?null:customerType);
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail == null ? null : (customerEmail.trim().length()==0?null:customerEmail);
    }

    public String getCustomerProfession() {
        return customerProfession;
    }

    public void setCustomerProfession(String customerProfession) {
        this.customerProfession = customerProfession == null ? null : (customerProfession.trim().length()==0?null:customerProfession);
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity == null ? null : (customerCity.trim().length()==0?null:customerCity);
    }

    public String getKnowWay() {
        return knowWay;
    }

    public void setKnowWay(String knowWay) {
        this.knowWay = knowWay == null ? null : (knowWay.trim().length()==0?null:knowWay);
    }
}