package bean;

import java.io.Serializable;

/**
 *
 * databaseTable:consultant
 */
public class Consultant implements Serializable{

	/**
     *   顾问ID
     */
    private String consultantId;

    /**
     *   服务商ID
     */
    private String providerId;

    /**
     *   顾问姓名
     */
    private String consultantName;

    /**
     *   顾问性别
     */
    private String consultantGender;

    /**
     *   头像路径
     */
    private String consultantImage;

    /**
     *   身份证号
     */
    private String consultantIdentity;

    /**
     *   入职日期
     */
    private String joinedDate;

    /**
     *   综合描述
     */
    private String consultantDescribe;

    /**
     *   手机号
     */
    private String consultantPhone;
    
    
    /**
     *   联系方式
     */
    private String contactPhone;
    
    /**
     *  主管名称
     */
    private String masterName;
    
    /**
     *  主管手机号
     */
    private String masterPhone;
    
    /**
     *   验证码
     */
    private String validateNum;
    
    /**
     *   验证码过期时间
     */
    private String validateTime;

    /**
     *   顾问类型
     */
    private String consultantType;

    /**
     *   顾问密码
     */
    private String passWord;

    /**
     *   服务区域
     */
    private String serviceArea;
    
    /**
     *   服务范围，西文“,"分离
     */
    private String serviceContent;

    /**
     *   服务数量
     */
    private Integer serviceCount;

    /**
     *   好评率
     */
    private String favorableRate;
    
    /**
     * 关注顾问的客户总数
     */
    private Integer byCustomerCount;
    
    public  String findKey(){
    	return consultantId;
    }
    
    public String getMasterName() {
		return masterName;
	}


	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}

	public String getMasterPhone() {
		return masterPhone;
	}

	public void setMasterPhone(String masterPhone) {
		this.masterPhone = masterPhone;
	}

	public Integer getByCustomerCount() {
		return byCustomerCount;
	}


	public void setByCustomerCount(Integer byCustomerCount) {
		this.byCustomerCount = byCustomerCount;
	}


	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}


	public String getValidateNum() {
		return validateNum;
	}

	public void setValidateNum(String validateNum) {
		this.validateNum = validateNum;
	}

	public String getValidateTime() {
		return validateTime;
	}


	public void setValidateTime(String validateTime) {
		this.validateTime = validateTime;
	}



	public String getServiceContent() {
		return serviceContent;
	}


	public void setServiceContent(String serviceContent) {
		this.serviceContent = serviceContent;
	}


	public String getConsultantId() {
        return consultantId;
    }

    public void setConsultantId(String consultantId) {
        this.consultantId = consultantId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getConsultantName() {
        return consultantName;
    }

    public void setConsultantName(String consultantName) {
        this.consultantName = consultantName;
    }

    public String getConsultantGender() {
        return consultantGender;
    }

    public void setConsultantGender(String consultantGender) {
        this.consultantGender = consultantGender;
    }

    public String getConsultantImage() {
        return consultantImage;
    }

    public void setConsultantImage(String consultantImage) {
        this.consultantImage = consultantImage;
    }

    public String getConsultantIdentity() {
        return consultantIdentity;
    }

    public void setConsultantIdentity(String consultantIdentity) {
        this.consultantIdentity = consultantIdentity;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public String getConsultantDescribe() {
        return consultantDescribe;
    }

    public void setConsultantDescribe(String consultantDescribe) {
        this.consultantDescribe = consultantDescribe;
    }

    public String getConsultantPhone() {
        return consultantPhone;
    }

    public void setConsultantPhone(String consultantPhone) {
        this.consultantPhone = consultantPhone;
    }

    public String getConsultantType() {
        return consultantType;
    }

    public void setConsultantType(String consultantType) {
        this.consultantType = consultantType;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getServiceArea() {
        return serviceArea;
    }

    public void setServiceArea(String serviceArea) {
        this.serviceArea = serviceArea;
    }

    public Integer getServiceCount() {
        return serviceCount;
    }

    public void setServiceCount(Integer serviceCount) {
        this.serviceCount = serviceCount;
    }

    public String getFavorableRate() {
        return favorableRate;
    }

    public void setFavorableRate(String favorableRate) {
        this.favorableRate = favorableRate;
    }
}