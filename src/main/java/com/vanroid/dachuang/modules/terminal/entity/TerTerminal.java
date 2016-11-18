/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 终端信息Entity
 *
 * @author CGZ
 * @version 2016-11-17
 */
public class TerTerminal extends DataEntity<TerTerminal> {

    private static final long serialVersionUID = 1L;
    private Date downDate;        // 下机日期
    private String terminalNum;        // 终端号
    private String deviceType;        // 机型号
    private String deviceNum;        // 机身号
    private String installPhone;        // 装机电话
    private String machineType;        // 机具类型
    private String installPerson;        // 装机人
    private String commCardNum;        // 通讯卡号
    private String terminalRemarks;        // 备注
    private String merchantNum;        // 商户号
    private String merchantId;        // 商户ID

    public TerTerminal() {
        super();
    }

    public TerTerminal(String id) {
        super(id);
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date getDownDate() {
        return downDate;
    }

    public void setDownDate(Date downDate) {
        this.downDate = downDate;
    }

    @Length(min = 1, max = 20, message = "终端号长度必须介于 1 和 20 之间")
    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    @Length(min = 0, max = 20, message = "机型号长度必须介于 0 和 20 之间")
    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    @Length(min = 0, max = 25, message = "机身号长度必须介于 0 和 25 之间")
    public String getDeviceNum() {
        return deviceNum;
    }

    public void setDeviceNum(String deviceNum) {
        this.deviceNum = deviceNum;
    }

    @Length(min = 0, max = 18, message = "装机电话长度必须介于 0 和 18 之间")
    public String getInstallPhone() {
        return installPhone;
    }

    public void setInstallPhone(String installPhone) {
        this.installPhone = installPhone;
    }

    @Length(min = 0, max = 20, message = "机具类型长度必须介于 0 和 20 之间")
    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    @Length(min = 0, max = 10, message = "装机人长度必须介于 0 和 10 之间")
    public String getInstallPerson() {
        return installPerson;
    }

    public void setInstallPerson(String installPerson) {
        this.installPerson = installPerson;
    }

    @Length(min = 0, max = 100, message = "通讯卡号长度必须介于 0 和 100 之间")
    public String getCommCardNum() {
        return commCardNum;
    }

    public void setCommCardNum(String commCardNum) {
        this.commCardNum = commCardNum;
    }


    public String getTerminalRemarks() {
        return terminalRemarks;
    }

    public void setTerminalRemarks(String terminalRemarks) {
        this.terminalRemarks = terminalRemarks;
    }

    @Length(min = 1, max = 20, message = "商户号长度必须介于 1 和 20 之间")
    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }

    @Length(min = 1, max = 64, message = "商户ID长度必须介于 1 和 64 之间")
    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    @Override
    public int hashCode() {
        return this.getTerminalNum().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TerTerminal) {
            TerTerminal terTerminal = (TerTerminal) obj;
            return terTerminal.getTerminalNum().equals(this.getTerminalNum());
        }
        return false;
    }
}