/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import com.vanroid.dachuang.modules.terminal.entity.TerTerminal;

import java.util.Date;
import java.util.List;

/**
 * 月帐单DAO接口
 *
 * @author CGZ
 * @version 2016-11-08
 */
@MyBatisDao
public interface TerBillMonthDao extends CrudDao<TerBillMonth> {

    /**
     * 通过月份删除记录
     *
     * @param clrDate 格式yyyyMM
     * @return
     */
    int deleteByClearDate(Date clrDate);

    int batchSave(List<TerBillMonth> datas);

    List<TerTerminal> findFailList(TerTerminal terTerminal);
}