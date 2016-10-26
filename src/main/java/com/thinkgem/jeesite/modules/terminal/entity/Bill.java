package com.thinkgem.jeesite.modules.terminal.entity;


import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * Created by cgz on 16-10-25.
 * 端终的消费帐单
 */
public class Bill extends DataEntity<Bill> {
    /**
     * 清算日期
     */
    private String date;

    /**
     * 交易代码
     */
    private String transId;

    /**
     * 受理流水
     */
    private String handleFlow;

    /**
     * 交易日期时间
     */
    private String transTime;

    /**
     * 卡号
     */
    private String cardNum;

    /**
     * 交易金额
     */
    private double transAmount;

    /**
     * 参考号
     */
    private String referNum;

    /**
     * 授权码
     */
    private String authorCode;

    /**
     * 终端号
     */
    private String terminalNum;

    /**
     * 商户编号
     */
    private String shopNum;

    /**
     * 商户名称
     */
    private String shopName;

    /**
     * 商户借记手续费
     */
    private String debitPoundage;

    /**
     * 商户贷记手续费
     */
    private String creditPoundage;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getHandleFlow() {
        return handleFlow;
    }

    public void setHandleFlow(String handleFlow) {
        this.handleFlow = handleFlow;
    }

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public double getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(double transAmount) {
        this.transAmount = transAmount;
    }

    public String getReferNum() {
        return referNum;
    }

    public void setReferNum(String referNum) {
        this.referNum = referNum;
    }

    public String getAuthorCode() {
        return authorCode;
    }

    public void setAuthorCode(String authorCode) {
        this.authorCode = authorCode;
    }

    public String getTerminalNum() {
        return terminalNum;
    }

    public void setTerminalNum(String terminalNum) {
        this.terminalNum = terminalNum;
    }

    public String getShopNum() {
        return shopNum;
    }

    public void setShopNum(String shopNum) {
        this.shopNum = shopNum;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDebitPoundage() {
        return debitPoundage;
    }

    public void setDebitPoundage(String debitPoundage) {
        this.debitPoundage = debitPoundage;
    }

    public String getCreditPoundage() {
        return creditPoundage;
    }

    public void setCreditPoundage(String creditPoundage) {
        this.creditPoundage = creditPoundage;
    }
}
