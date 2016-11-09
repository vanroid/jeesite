/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import com.vanroid.dachuang.common.excel.filetype.BillDateType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 月帐单Entity
 *
 * @author CGZ
 * @version 2016-11-08
 */
public class TerBillMonth extends DataEntity<TerBillMonth> {

    private static final long serialVersionUID = 1L;
    @ExcelField(title = "清算日期", fieldType = BillDateType.class, sort = 0)
    private Date clearDate;        // 清算日期
    @ExcelField(title = "商户编号", sort = 1)
    private String merchantNum;        // 商户编号
    @ExcelField(title = "终端号", sort = 2)
    private String terminalNum;        // 终端号
    @ExcelField(title = "收单行", sort = 3)
    private String acquiringBank;        // 收单行
    @ExcelField(title = "收单行简称", sort = 4)
    private String acquiringNick;        // 收单行简称
    @ExcelField(title = "商户名称", sort = 5)
    private String merchantName;        // 商户名称
    @ExcelField(title = "维护公司代码", sort = 6)
    private String maintenanceCompany;        // 维护公司代码
    @ExcelField(title = "维护公司简称", sort = 7)
    private String maintenanceCompanyNick;        // 维护公司简称
    @ExcelField(title = "总笔数", sort = 8)
    private String totalTimes;        // 总笔数
    @ExcelField(title = "总金额", sort = 9)
    private String totalAmount;        // 总金额
    @ExcelField(title = "IC卡笔数", sort = 10)
    private String icTimes;        // IC卡笔数
    @ExcelField(title = "IC卡金额", sort = 11)
    private String icAmount;        // IC卡金额
    @ExcelField(title = "非接联机笔数", sort = 12)
    private String nonOnlineTimes;        // 非接联机笔数
    @ExcelField(title = "非接联机金额", sort = 13)
    private String nonOnlineAmount;        // 非接联机金额
    @ExcelField(title = "非接脱机笔数", sort = 14)
    private String nonOfflineTimes;        // 非接脱机笔数
    @ExcelField(title = "非接脱机金额", sort = 15)
    private String nonOfflineAmount;        // 非接脱机金额
    @ExcelField(title = "云闪付笔数", sort = 16)
    private String cloudTimes;        // 云闪付笔数
    @ExcelField(title = "云闪付金额", sort = 17)
    private String cloudAmount;        // 云闪付金额
    @ExcelField(title = "Appl笔数", sort = 18)
    private String applTimes;        // Appl笔数
    @ExcelField(title = "Appl金额", sort = 19)
    private String applAmount;        // Appl金额
    @ExcelField(title = "HCE笔数", sort = 20)
    private String hceTimes;        // HCE笔数
    @ExcelField(title = "HCE金额", sort = 21)
    private String hceAmount;        // HCE金额
    @ExcelField(title = "Sams笔数", sort = 22)
    private String samsTimes;        // Sams笔数
    @ExcelField(title = "Sams金额", sort = 23)
    private String samsAmount;        // Sams金额

    public TerBillMonth() {
        super();
    }

    public TerBillMonth(String id) {
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

    @Length(min = 1, max = 20, message = "商户编号长度必须介于 1 和 20 之间")
    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    @Length(min = 1, max = 20, message = "终端号长度必须介于 1 和 20 之间")
    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    @Length(min = 1, max = 10, message = "收单行长度必须介于 1 和 10 之间")
    public String getAcquiringBank() {
        return acquiringBank;
    }

    public void setAcquiringBank(String acquiringBank) {
        this.acquiringBank = acquiringBank;
    }

    @Length(min = 1, max = 10, message = "收单行简称长度必须介于 1 和 10 之间")
    public String getAcquiringNick() {
        return acquiringNick;
    }

    public void setAcquiringNick(String acquiringNick) {
        this.acquiringNick = acquiringNick;
    }

    @Length(min = 0, max = 100, message = "商户名称长度必须介于 0 和 100 之间")
    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    @Length(min = 0, max = 10, message = "维护公司代码长度必须介于 0 和 10 之间")
    public String getMaintenanceCompany() {
        return maintenanceCompany;
    }

    public void setMaintenanceCompany(String maintenanceCompany) {
        this.maintenanceCompany = maintenanceCompany;
    }

    @Length(min = 0, max = 10, message = "维护公司简称长度必须介于 0 和 10 之间")
    public String getMaintenanceCompanyNick() {
        return maintenanceCompanyNick;
    }

    public void setMaintenanceCompanyNick(String maintenanceCompanyNick) {
        this.maintenanceCompanyNick = maintenanceCompanyNick;
    }

    @Length(min = 0, max = 11, message = "总笔数长度必须介于 0 和 11 之间")
    public String getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(String totalTimes) {
        this.totalTimes = totalTimes;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Length(min = 0, max = 11, message = "IC卡笔数长度必须介于 0 和 11 之间")
    public String getIcTimes() {
        return icTimes;
    }

    public void setIcTimes(String icTimes) {
        this.icTimes = icTimes;
    }

    public String getIcAmount() {
        return icAmount;
    }

    public void setIcAmount(String icAmount) {
        this.icAmount = icAmount;
    }

    @Length(min = 0, max = 11, message = "非接联机笔数长度必须介于 0 和 11 之间")
    public String getNonOnlineTimes() {
        return nonOnlineTimes;
    }

    public void setNonOnlineTimes(String nonOnlineTimes) {
        this.nonOnlineTimes = nonOnlineTimes;
    }

    public String getNonOnlineAmount() {
        return nonOnlineAmount;
    }

    public void setNonOnlineAmount(String nonOnlineAmount) {
        this.nonOnlineAmount = nonOnlineAmount;
    }

    @Length(min = 0, max = 11, message = "非接脱机笔数长度必须介于 0 和 11 之间")
    public String getNonOfflineTimes() {
        return nonOfflineTimes;
    }

    public void setNonOfflineTimes(String nonOfflineTimes) {
        this.nonOfflineTimes = nonOfflineTimes;
    }

    public String getNonOfflineAmount() {
        return nonOfflineAmount;
    }

    public void setNonOfflineAmount(String nonOfflineAmount) {
        this.nonOfflineAmount = nonOfflineAmount;
    }

    @Length(min = 0, max = 11, message = "云闪付笔数长度必须介于 0 和 11 之间")
    public String getCloudTimes() {
        return cloudTimes;
    }

    public void setCloudTimes(String cloudTimes) {
        this.cloudTimes = cloudTimes;
    }

    public String getCloudAmount() {
        return cloudAmount;
    }

    public void setCloudAmount(String cloudAmount) {
        this.cloudAmount = cloudAmount;
    }

    @Length(min = 0, max = 11, message = "Appl笔数长度必须介于 0 和 11 之间")
    public String getApplTimes() {
        return applTimes;
    }

    public void setApplTimes(String applTimes) {
        this.applTimes = applTimes;
    }

    public String getApplAmount() {
        return applAmount;
    }

    public void setApplAmount(String applAmount) {
        this.applAmount = applAmount;
    }

    @Length(min = 0, max = 11, message = "HCE笔数长度必须介于 0 和 11 之间")
    public String getHceTimes() {
        return hceTimes;
    }

    public void setHceTimes(String hceTimes) {
        this.hceTimes = hceTimes;
    }

    public String getHceAmount() {
        return hceAmount;
    }

    public void setHceAmount(String hceAmount) {
        this.hceAmount = hceAmount;
    }

    @Length(min = 0, max = 11, message = "Sams笔数长度必须介于 0 和 11 之间")
    public String getSamsTimes() {
        return samsTimes;
    }

    public void setSamsTimes(String samsTimes) {
        this.samsTimes = samsTimes;
    }

    public String getSamsAmount() {
        return samsAmount;
    }

    public void setSamsAmount(String samsAmount) {
        this.samsAmount = samsAmount;
    }

}