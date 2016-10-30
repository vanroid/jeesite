/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 消费日流水Entity
 * @author CGZ
 * @version 2016-10-30
 */
public class TerBillDay extends DataEntity<TerBillDay> {
	
	private static final long serialVersionUID = 1L;
	private Date clearDate;		// 清算日期
	private String tranCode;		// 交易代码
	private String handleCode;		// 受理流水
	private String tranDateTime;		// 交易日期时间
	private String cardNo;		// 卡号
	private String tranAmount;		// 交易金额
	private String referCode;		// 参考号
	private String grantCode;		// 授权码
	private String terminalNum;		// 终端号
	private String merchantNum;		// 商户编号
	private String merchantName;		// 商户名称
	private String debitFee;		// 商户借记手续费
	private String creditFee;		// 商户贷记手续费
	private Date beginClearDate;		// 开始 清算日期
	private Date endClearDate;		// 结束 清算日期
	
	public TerBillDay() {
		super();
	}

	public TerBillDay(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="清算日期不能为空")
	public Date getClearDate() {
		return clearDate;
	}

	public void setClearDate(Date clearDate) {
		this.clearDate = clearDate;
	}
	
	@Length(min=1, max=4, message="交易代码长度必须介于 1 和 4 之间")
	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
	
	@Length(min=0, max=10, message="受理流水长度必须介于 0 和 10 之间")
	public String getHandleCode() {
		return handleCode;
	}

	public void setHandleCode(String handleCode) {
		this.handleCode = handleCode;
	}
	
	@Length(min=0, max=12, message="交易日期时间长度必须介于 0 和 12 之间")
	public String getTranDateTime() {
		return tranDateTime;
	}

	public void setTranDateTime(String tranDateTime) {
		this.tranDateTime = tranDateTime;
	}
	
	@Length(min=1, max=20, message="卡号长度必须介于 1 和 20 之间")
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	
	public String getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(String tranAmount) {
		this.tranAmount = tranAmount;
	}
	
	@Length(min=0, max=20, message="参考号长度必须介于 0 和 20 之间")
	public String getReferCode() {
		return referCode;
	}

	public void setReferCode(String referCode) {
		this.referCode = referCode;
	}
	
	@Length(min=0, max=10, message="授权码长度必须介于 0 和 10 之间")
	public String getGrantCode() {
		return grantCode;
	}

	public void setGrantCode(String grantCode) {
		this.grantCode = grantCode;
	}
	
	@Length(min=1, max=20, message="终端号长度必须介于 1 和 20 之间")
	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	
	@Length(min=1, max=20, message="商户编号长度必须介于 1 和 20 之间")
	public String getMerchantNum() {
		return merchantNum;
	}

	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}
	
	@Length(min=0, max=100, message="商户名称长度必须介于 0 和 100 之间")
	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	
	public String getDebitFee() {
		return debitFee;
	}

	public void setDebitFee(String debitFee) {
		this.debitFee = debitFee;
	}
	
	public String getCreditFee() {
		return creditFee;
	}

	public void setCreditFee(String creditFee) {
		this.creditFee = creditFee;
	}
	
	public Date getBeginClearDate() {
		return beginClearDate;
	}

	public void setBeginClearDate(Date beginClearDate) {
		this.beginClearDate = beginClearDate;
	}
	
	public Date getEndClearDate() {
		return endClearDate;
	}

	public void setEndClearDate(Date endClearDate) {
		this.endClearDate = endClearDate;
	}
		
}