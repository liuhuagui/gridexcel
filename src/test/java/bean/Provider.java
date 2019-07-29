package bean;

import java.io.Serializable;
import java.util.List;



/**
 *
 * databaseTable:provider
 */
public class Provider implements Serializable {
	/**
     *   服务商ID
     */
    private String providerId;

    /**
     *   服务商名称
     */
    private String providerName;
    
    /**
     *   服务商性质
     */
    private String providerCharacter;
    
    /**
     *   头像路径
     */
    private String providerImage;

    /**
     *   描述
     */
    private String providerDescribe;

    /**
     *   企业地址
     */
    private String providerAddress;

    /**
     *   企业邮箱
     */
    private String providerEmail;

    /**
     *   法人姓名
     */
    private String legalPersonName;

    /**
     *   法人性别
     */
    private String legalPersonGender;

    /**
     *   法人身份证号
     */
    private String legalPersonIdentityNum;

    /**
     *   身份证正面照路径
     */
    private String legalPersonIdentityFore;

    /**
     *   身份证背面照路径
     */
    private String legalPersonIdentityBack;

    /**
     *   法人手机号
     */
    private String legalPersonPhone;

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
     *   过期时间
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
     *   经营许可证编号
     */
    private String businessLicenceNum;

    /**
     *   经营许可证路径
     */
    private String businessLicenceUrl;
    
    /**
     * 服务商下顾问的初始密码
     */
    private String defaultPassWord;

    /**
     * 服务商状态
     */
    private String providerType;
    
    /**
     * 未读通知数
     */
    private Integer unreadNotificationCount;
    
    /**
     * 已读通知数
     */
    private Integer readNotificationCount;
    
    /**
     * 订单总数
     */
    private Integer orderQuantity;
    
    /**
     * 待结算订单总数
     */
    private Integer pendingSettlementQuantity;
    
    /**
     * 服务上下顾问总数
     */
    private Integer consultantQuantity;
    
    /**
     * 服务项目总数
     */
    private Integer serviceQuantity;
    

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

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Integer getPendingSettlementQuantity() {
		return pendingSettlementQuantity;
	}

	public void setPendingSettlementQuantity(Integer pendingSettlementQuantity) {
		this.pendingSettlementQuantity = pendingSettlementQuantity;
	}

	public Integer getConsultantQuantity() {
		return consultantQuantity;
	}

	public void setConsultantQuantity(Integer consultantQuantity) {
		this.consultantQuantity = consultantQuantity;
	}

	public Integer getServiceQuantity() {
		return serviceQuantity;
	}

	public void setServiceQuantity(Integer serviceQuantity) {
		this.serviceQuantity = serviceQuantity;
	}

	public String getProviderCharacter() {
		return providerCharacter;
	}

	public void setProviderCharacter(String providerCharacter) {
		this.providerCharacter = providerCharacter;
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


	public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : (providerId.trim().length()==0?null:providerId);
    }

    public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getProviderType() {
		return providerType;
	}

	public void setProviderType(String providerType) {
		this.providerType = providerType;
	}

	public String getDefaultPassWord() {
		return defaultPassWord;
	}

	public void setDefaultPassWord(String defaultPassWord) {
		this.defaultPassWord = defaultPassWord;
	}

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : (providerName.trim().length()==0?null:providerName);
    }

    public String getProviderImage() {
        return providerImage;
    }

    public void setProviderImage(String providerImage) {
        this.providerImage = providerImage == null ? null : (providerImage.trim().length()==0?null:providerImage);
    }

    public String getProviderDescribe() {
        return providerDescribe;
    }

    public void setProviderDescribe(String providerDescribe) {
        this.providerDescribe = providerDescribe == null ? null : (providerDescribe.trim().length()==0?null:providerDescribe);
    }

    public String getProviderAddress() {
        return providerAddress;
    }

    public void setProviderAddress(String providerAddress) {
        this.providerAddress = providerAddress == null ? null : (providerAddress.trim().length()==0?null:providerAddress);
    }

    public String getProviderEmail() {
        return providerEmail;
    }

    public void setProviderEmail(String providerEmail) {
        this.providerEmail = providerEmail == null ? null : (providerEmail.trim().length()==0?null:providerEmail);
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : (legalPersonName.trim().length()==0?null:legalPersonName);
    }

    public String getLegalPersonGender() {
        return legalPersonGender;
    }

    public void setLegalPersonGender(String legalPersonGender) {
        this.legalPersonGender = legalPersonGender == null ? null : (legalPersonGender.trim().length()==0?null:legalPersonGender);
    }

    public String getLegalPersonIdentityNum() {
        return legalPersonIdentityNum;
    }

    public void setLegalPersonIdentityNum(String legalPersonIdentityNum) {
        this.legalPersonIdentityNum = legalPersonIdentityNum == null ? null : (legalPersonIdentityNum.trim().length()==0?null:legalPersonIdentityNum);
    }

    public String getLegalPersonIdentityFore() {
        return legalPersonIdentityFore;
    }

    public void setLegalPersonIdentityFore(String legalPersonIdentityFore) {
        this.legalPersonIdentityFore = legalPersonIdentityFore == null ? null : (legalPersonIdentityFore.trim().length()==0?null:legalPersonIdentityFore);
    }

    public String getLegalPersonIdentityBack() {
        return legalPersonIdentityBack;
    }

    public void setLegalPersonIdentityBack(String legalPersonIdentityBack) {
        this.legalPersonIdentityBack = legalPersonIdentityBack == null ? null : (legalPersonIdentityBack.trim().length()==0?null:legalPersonIdentityBack);
    }

    public String getLegalPersonPhone() {
        return legalPersonPhone;
    }

    public void setLegalPersonPhone(String legalPersonPhone) {
        this.legalPersonPhone = legalPersonPhone == null ? null : (legalPersonPhone.trim().length()==0?null:legalPersonPhone);
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

    public String getBusinessLicenceNum() {
        return businessLicenceNum;
    }

    public void setBusinessLicenceNum(String businessLicenceNum) {
        this.businessLicenceNum = businessLicenceNum == null ? null : (businessLicenceNum.trim().length()==0?null:businessLicenceNum);
    }

    public String getBusinessLicenceUrl() {
        return businessLicenceUrl;
    }

    public void setBusinessLicenceUrl(String businessLicenceUrl) {
        this.businessLicenceUrl = businessLicenceUrl == null ? null : (businessLicenceUrl.trim().length()==0?null:businessLicenceUrl);
    }
}