/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.vanroid.dachuang.common.excel.fieldtype.UserType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 终端设备Entity
 *
 * @author CGZ
 * @version 2016-10-26
 */
public class PosTerminal extends DataEntity<PosTerminal> {

    private static final long serialVersionUID = 1L;
    private Date importDate;        // 进件日期
    private Date downDate;        // 下机日期
    private User user;        // 所属用户
    private Date installDate;        // 装机日期
    private String transBank;        // 交件支行
    private String deviceNum;        // 机身号
    private String deviceType;        // 机子类型
    private String merchantNum;        // 商户号,重要字段
    private String terminalNum;        // 终端号,重要字段
    private String wechatUrl;        // 微信二维码
    private String businessLicense;        // 营业执照
    private String merchantName;        // 商户名称
    private String merchantAddress;        // 地址
    private String merchantLegalPerson;        // 法人
    private String bookingPerson;        // 入帐人
    private String telphone;        // 联系电话
    private String installPhone;        // 装机电话
    private String deviceMcc;        // MCC码
    private String debitRate;        // 借记卡费率
    private String creditRate;        // 贷记卡费率
    private String foreignRate;        // 外币卡费率
    private String idCard;        // 身份证号
    private String machineType;        // 机具类型
    private String bankCard;        // 银行卡号
    private String bankCardAccountBank;        // 银行卡开户行
    private String salesman;        // 业务员
    private String terminalDesc;        // 详情

    public PosTerminal() {
        super();
    }

    public PosTerminal(String id) {
        super(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd")
    @ExcelField(title = "进件日期", sort = 0)
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    @ExcelField(title = "进件日期", sort = 1)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    @ExcelField(title = "所属用户", sort = 2, fieldType = UserType.class)
    @NotNull(message = "所属用户不能为空")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ExcelField(title = "安装日期", sort = 3)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getInstallDate() {
        return installDate;
    }

    public void setInstallDate(Date installDate) {
        this.installDate = installDate;
    }

    @ExcelField(title = "交件支行", sort = 4)
    @Length(min = 0, max = 20, message = "交件支行长度必须介于 0 和 20 之间")
    public String getTransBank() {
        return transBank;
    }

    public void setTransBank(String transBank) {
        this.transBank = transBank;
    }

    @ExcelField(title = "机身号", sort = 5)
    @Length(min = 0, max = 25, message = "机身号长度必须介于 0 和 25 之间")
    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    @ExcelField(title = "机子类型", sort = 6)
    @Length(min = 0, max = 20, message = "机子类型长度必须介于 0 和 20 之间")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @ExcelField(title = "商户号", sort = 7)
    @Length(min = 1, max = 20, message = "商户号,重要字段长度必须介于 1 和 20 之间")
    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    @ExcelField(title = "终端号", sort = 8)
    @Length(min = 1, max = 20, message = "终端号,重要字段长度必须介于 1 和 20 之间")
    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    @ExcelField(title = "微信二维码", sort = 9)
    @Length(min = 0, max = 512, message = "微信二维码长度必须介于 0 和 512 之间")
    public String getWechatUrl() {
        return wechatUrl;
    }

    public void setWechatUrl(String wechatUrl) {
        this.wechatUrl = wechatUrl;
    }

    @ExcelField(title = "营业执照", sort = 10)
    @Length(min = 0, max = 512, message = "营业执照长度必须介于 0 和 512 之间")
    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    @ExcelField(title = "商户名称", sort = 11)
    @Length(min = 0, max = 20, message = "商户名称长度必须介于 0 和 20 之间")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @ExcelField(title = "地址", sort = 12)
    @Length(min = 0, max = 512, message = "地址长度必须介于 0 和 512 之间")
    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    @ExcelField(title = "法人", sort = 13)
    @Length(min = 0, max = 10, message = "法人长度必须介于 0 和 10 之间")
    public String getMerchantLegalPerson() {
        return merchantLegalPerson;
    }

    public void setMerchantLegalPerson(String merchantLegalPerson) {
        this.merchantLegalPerson = merchantLegalPerson;
    }

    @ExcelField(title = "入帐人", sort = 14)
    @Length(min = 0, max = 10, message = "入帐人长度必须介于 0 和 10 之间")
    public String getBookingPerson() {
        return bookingPerson;
    }

    public void setBookingPerson(String bookingPerson) {
        this.bookingPerson = bookingPerson;
    }

    @ExcelField(title = "联系电话", sort = 15)
    @Length(min = 0, max = 100, message = "联系电话长度必须介于 0 和 100 之间")
    public String getTelphone() {
        return telphone;
    }

    public void setTelphone(String telphone) {
        this.telphone = telphone;
    }

    @ExcelField(title = "装机电话", sort = 16)
    @Length(min = 0, max = 18, message = "装机电话长度必须介于 0 和 18 之间")
    public String getInstallPhone() {
        return installPhone;
    }

    public void setInstallPhone(String installPhone) {
        this.installPhone = installPhone;
    }

    @ExcelField(title = "MCC码", sort = 17)
    @Length(min = 0, max = 10, message = "MCC码长度必须介于 0 和 10 之间")
    public String getDeviceMcc() {
        return deviceMcc;
    }

    public void setDeviceMcc(String deviceMcc) {
        this.deviceMcc = deviceMcc;
    }

    @ExcelField(title = "借记卡费率", sort = 18)
    @Length(min = 0, max = 20, message = "借记卡费率长度必须介于 0 和 20 之间")
    public String getDebitRate() {
        return debitRate;
    }

    public void setDebitRate(String debitRate) {
        this.debitRate = debitRate;
    }

    @ExcelField(title = "贷记卡费率", sort = 19)
    @Length(min = 0, max = 20, message = "贷记卡费率长度必须介于 0 和 20 之间")
    public String getCreditRate() {
        return creditRate;
    }

    public void setCreditRate(String creditRate) {
        this.creditRate = creditRate;
    }

    @ExcelField(title = "外币卡费率", sort = 20)
    @Length(min = 0, max = 20, message = "外币卡费率长度必须介于 0 和 20 之间")
    public String getForeignRate() {
        return foreignRate;
    }

    public void setForeignRate(String foreignRate) {
        this.foreignRate = foreignRate;
    }

    @ExcelField(title = "身份证号", sort = 21)
    @Length(min = 0, max = 18, message = "身份证号长度必须介于 0 和 18 之间")
    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    @ExcelField(title = "机具类型", sort = 22)
    @Length(min = 0, max = 20, message = "机具类型长度必须介于 0 和 20 之间")
    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    @ExcelField(title = "银行卡号", sort = 23)
    @Length(min = 0, max = 100, message = "银行卡号长度必须介于 0 和 100 之间")
    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    @ExcelField(title = "银行卡开户行", sort = 24)
    @Length(min = 0, max = 18, message = "银行卡开户行长度必须介于 0 和 18 之间")
    public String getBankCardAccountBank() {
        return bankCardAccountBank;
    }

    public void setBankCardAccountBank(String bankCardAccountBank) {
        this.bankCardAccountBank = bankCardAccountBank;
    }

    @ExcelField(title = "业务员", sort = 25)
    @Length(min = 0, max = 20, message = "业务员长度必须介于 0 和 20 之间")
    public String getSalesman() {
        return salesman;
    }

    public void setSalesman(String salesman) {
        this.salesman = salesman;
    }

    @ExcelField(title = "详情", sort = 26)
    public String getTerminalDesc() {
        return terminalDesc;
    }

    public void setTerminalDesc(String terminalDesc) {
        this.terminalDesc = terminalDesc;
    }

}