/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 终端设备Entity
 * @author CGZ
 * @version 2016-10-26
 */
public class PosTerminal extends DataEntity<PosTerminal> {
	
	private static final long serialVersionUID = 1L;
	private Date importDate;		// 进件日期
	private Date downDate;		// 下机日期
	private User user;		// 所属用户
	private Date installDate;		// 装机日期
	private String transBank;		// 交件支行
	private String deviceNum;		// 机身号
	private String deviceType;		// 机子类型
	private String merchantNum;		// 商户号,重要字段
	private String termialNum;		// 终端号,重要字段
	private String wechatUrl;		// 微信二维码
	private String businessLicense;		// 营业执照
	private String merchantName;		// 商户名称
	private String merchantAddress;		// 地址
	private String merchantLegalPerson;		// 法人
	private String bookingPerson;		// 入帐人
	private String telphone;		// 联系电话
	private String installPhone;		// 装机电话
	private String deviceMcc;		// MCC码
	private String debitRate;		// 借记卡费率
	private String creditRate;		// 贷记卡费率
	private String foreignRate;		// 外币卡费率
	private String idCard;		// 身份证号
	private String machineType;		// 机具类型
	private String bankCard;		// 银行卡号
	private String bankCardAccountBank;		// 银行卡开户行
	private String salesman;		// 业务员
	private String terminalDesc;		// 详情
	
	public PosTerminal() {
		super();
	}

	public PosTerminal(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//@ExcelField(title="进件日期", sort=0, fieldType=Date.class)
	public Date getImportDate() {
		return importDate;
	}

	public void setImportDate(Date importDate) {
		this.importDate = importDate;
	}

	//@ExcelField(title="进件日期", sort=1, fieldType=Date.class)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getDownDate() {
		return downDate;
	}

	public void setDownDate(Date downDate) {
		this.downDate = downDate;
	}

	@NotNull(message="所属用户不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}
	
	@Length(min=0, max=20, message="交件支行长度必须介于 0 和 20 之间")
	public String getTransBank() {
		return transBank;
	}

	public void setTransBank(String transBank) {
		this.transBank = transBank;
	}
	
	@Length(min=0, max=25, message="机身号长度必须介于 0 和 25 之间")
	public String getDeviceNum() {
		return deviceNum;
	}

	public void setDeviceNum(String deviceNum) {
		this.deviceNum = deviceNum;
	}
	
	@Length(min=0, max=20, message="机子类型长度必须介于 0 和 20 之间")
	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	
	@Length(min=1, max=20, message="商户号,重要字段长度必须介于 1 和 20 之间")
	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
	
	@Length(min=1, max=20, message="终端号,重要字段长度必须介于 1 和 20 之间")
	public String getTermialNum() {
		return termialNum;
	}

	public void setTermialNum(String termialNum) {
		this.termialNum = termialNum;
	}
	
	@Length(min=0, max=512, message="微信二维码长度必须介于 0 和 512 之间")
	public String getWechatUrl() {
		return wechatUrl;
	}

	public void setWechatUrl(String wechatUrl) {
		this.wechatUrl = wechatUrl;
	}
	
	@Length(min=0, max=512, message="营业执照长度必须介于 0 和 512 之间")
	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	
	@Length(min=0, max=20, message="商户名称长度必须介于 0 和 20 之间")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	@Length(min=0, max=512, message="地址长度必须介于 0 和 512 之间")
	public String getMerchantAddress() {
		return merchantAddress;
	}

	public void setMerchantAddress(String merchantAddress) {
		this.merchantAddress = merchantAddress;
	}
	
	@Length(min=0, max=10, message="法人长度必须介于 0 和 10 之间")
	public String getMerchantLegalPerson() {
		return merchantLegalPerson;
	}

	public void setMerchantLegalPerson(String merchantLegalPerson) {
		this.merchantLegalPerson = merchantLegalPerson;
	}
	
	@Length(min=0, max=10, message="入帐人长度必须介于 0 和 10 之间")
	public String getBookingPerson() {
		return bookingPerson;
	}

	public void setBookingPerson(String bookingPerson) {
		this.bookingPerson = bookingPerson;
	}
	
	@Length(min=0, max=18, message="联系电话长度必须介于 0 和 18 之间")
	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	
	@Length(min=0, max=18, message="装机电话长度必须介于 0 和 18 之间")
	public String getInstallPhone() {
		return installPhone;
	}

	public void setInstallPhone(String installPhone) {
		this.installPhone = installPhone;
	}
	
	@Length(min=0, max=10, message="MCC码长度必须介于 0 和 10 之间")
	public String getDeviceMcc() {
		return deviceMcc;
	}

	public void setDeviceMcc(String deviceMcc) {
		this.deviceMcc = deviceMcc;
	}
	
	@Length(min=0, max=20, message="借记卡费率长度必须介于 0 和 20 之间")
	public String getDebitRate() {
		return debitRate;
	}

	public void setDebitRate(String debitRate) {
		this.debitRate = debitRate;
	}
	
	@Length(min=0, max=20, message="贷记卡费率长度必须介于 0 和 20 之间")
	public String getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}
	
	@Length(min=0, max=20, message="外币卡费率长度必须介于 0 和 20 之间")
	public String getForeignRate() {
		return foreignRate;
	}

	public void setForeignRate(String foreignRate) {
		this.foreignRate = foreignRate;
	}
	
	@Length(min=0, max=18, message="身份证号长度必须介于 0 和 18 之间")
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
	@Length(min=0, max=20, message="机具类型长度必须介于 0 和 20 之间")
	public String getMachineType() {
		return machineType;
	}

	public void setMachineType(String machineType) {
		this.machineType = machineType;
	}
	
	@Length(min=0, max=100, message="银行卡号长度必须介于 0 和 100 之间")
	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	
	@Length(min=0, max=18, message="银行卡开户行长度必须介于 0 和 18 之间")
	public String getBankCardAccountBank() {
		return bankCardAccountBank;
	}

	public void setBankCardAccountBank(String bankCardAccountBank) {
		this.bankCardAccountBank = bankCardAccountBank;
	}
	
	@Length(min=0, max=20, message="业务员长度必须介于 0 和 20 之间")
	public String getSalesman() {
		return salesman;
	}

	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	
	public String getTerminalDesc() {
		return terminalDesc;
	}

	public void setTerminalDesc(String terminalDesc) {
		this.terminalDesc = terminalDesc;
	}
	
}