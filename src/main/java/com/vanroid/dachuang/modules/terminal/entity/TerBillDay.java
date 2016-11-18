/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.vanroid.dachuang.common.excel.filetype.BillDateType;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 消费日流水Entity
 *
 * @author CGZ
 * @version 2016-10-30
 *          清算日期	交易代码	受理流水	交易日期时间	卡号	交易金额	参考号	授权码	终端号	商户编号	商户名称	商户借记手续费	商户贷记手续费	卡性质
 */
public class TerBillDay extends DataEntity<TerBillDay> {

    private static final long serialVersionUID = 1L;
    @ExcelField(title = "清算日期", fieldType = BillDateType.class, sort = 0)
    private Date clearDate;        // 清算日期
    @ExcelField(title = "交易代码", sort = 1)
    private String tranCode;        // 交易代码
    @ExcelField(title = "受理流水", sort = 2)
    private String handleCode;        // 受理流水
    @ExcelField(title = "交易日期时间", sort = 3)
    private String tranDateTime;        // 交易日期时间
    @ExcelField(title = "卡号", sort = 4)
    private String cardNo;        // 卡号
    @ExcelField(title = "交易金额", sort = 5)
    private Double tranAmount;        // 交易金额
    @ExcelField(title = "参考号", sort = 6)
    private String referCode;        // 参考号
    @ExcelField(title = "授权码", sort = 7)
    private String grantCode;        // 授权码
    @ExcelField(title = "终端号", sort = 8)
    private String terminalNum;        // 终端号
    @ExcelField(title = "商户编号", sort = 9)
    private String merchantNum;        // 商户编号
    @ExcelField(title = "商户名称", sort = 10)
    private String merchantName;        // 商户名称
    @ExcelField(title = "商户借记手续费", sort = 11)
    private String debitFee;        // 商户借记手续费
    @ExcelField(title = "商户贷记手续费", sort = 12)
    private String creditFee;        // 商户贷记手续费
    @ExcelField(title = "卡性质", sort = 13)
    private String cardType;        // 卡性质
    private Date beginClearDate;        // 开始 清算日期
    private Date endClearDate;        // 结束 清算日期

    public TerBillDay() {
        super();
    }

    public TerBillDay(String id) {
        super(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @NotNull(message = "清算日期不能为空")
    public Date getClearDate() {
        return clearDate;
    }

    public void setClearDate(Date clearDate) {
        this.clearDate = clearDate;
    }

    @Length(min = 1, max = 10, message = "交易代码长度必须介于 1 和 10 之间")
    public String getTranCode() {
        return tranCode;
    }

    public void setTranCode(String tranCode) {
        this.tranCode = tranCode;
    }

    @Length(min = 0, max = 10, message = "受理流水长度必须介于 0 和 10 之间")
    public String getHandleCode() {
        return handleCode;
    }

    public void setHandleCode(String handleCode) {
        this.handleCode = handleCode;
    }

    @Length(min = 0, max = 12, message = "交易日期时间长度必须介于 0 和 12 之间")
    public String getTranDateTime() {
        return tranDateTime;
    }

    public void setTranDateTime(String tranDateTime) {
        this.tranDateTime = tranDateTime;
    }

    @Length(min = 1, max = 20, message = "卡号长度必须介于 1 和 20 之间")
    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public Double getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Double tranAmount) {
        this.tranAmount = tranAmount;
    }

    @Length(min = 0, max = 20, message = "参考号长度必须介于 0 和 20 之间")
    public String getReferCode() {
        return referCode;
    }

    public void setReferCode(String referCode) {
        this.referCode = referCode;
    }

    @Length(min = 0, max = 10, message = "授权码长度必须介于 0 和 10 之间")
    public String getGrantCode() {
        return grantCode;
    }

    public void setGrantCode(String grantCode) {
        this.grantCode = grantCode;
    }

    @Length(min = 1, max = 20, message = "终端号长度必须介于 1 和 20 之间")
    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    @Length(min = 1, max = 20, message = "商户编号长度必须介于 1 和 20 之间")
    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    @Length(min = 0, max = 100, message = "商户名称长度必须介于 0 和 100 之间")
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

    @Length(min = 0, max = 10, message = "商户名称长度必须介于 0 和 10 之间")
    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
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