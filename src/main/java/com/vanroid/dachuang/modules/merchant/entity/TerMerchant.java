/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 商户管理Entity
 * @author CGZ
 * @version 2016-11-16
 */
public class TerMerchant extends DataEntity<TerMerchant> {
	
	private static final long serialVersionUID = 1L;
	private String merchantNum;		// 商户号
	private String wechatUrl;		// 微信二维码
	private String businessLicense;		// 营业执照
	private String merchantName;		// 商户名称
	private String merchantAddress;		// 地址
	private String merchantLegalPerson;		// 法人
	private String bookingPerson;		// 入帐人
	private String telphone;		// 联系电话
	private String debitRate;		// 借记卡费率
	private String creditRate;		// 贷记卡费率
	private String foreignRate;		// 外币卡费率
	private String idCard;		// 身份证号
	private String bankCard;		// 银行卡号
	private String bankCardAccountBank;		// 银行卡开户行
	private String salesman;		// 业务员
	private String merchatDesc;		// 详情
	private Office office;		// 机构编号
	
	public TerMerchant() {
		super();
	}

	public TerMerchant(String id){
		super(id);
	}

	@Length(min=1, max=20, message="商户号长度必须介于 1 和 20 之间")
	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
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
	
	@Length(min=0, max=100, message="商户名称长度必须介于 0 和 100 之间")
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
	
	public String getMerchatDesc() {
		return merchatDesc;
	}

	public void setMerchatDesc(String merchatDesc) {
		this.merchatDesc = merchatDesc;
	}
	
	@NotNull(message="机构编号不能为空")
	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}
	
}