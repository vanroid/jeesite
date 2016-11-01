/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;

import java.util.List;

/**
 * 消费日流水DAO接口
 *
 * @author CGZ
 * @version 2016-10-30
 */
@MyBatisDao
public interface TerBillDayDao extends CrudDao<TerBillDay> {

    int batchInsert(List<TerBillDay> terBillDays);
}