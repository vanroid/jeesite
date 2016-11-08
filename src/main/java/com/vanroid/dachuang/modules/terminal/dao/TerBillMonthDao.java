/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;

/**
 * 月帐单DAO接口
 * @author CGZ
 * @version 2016-11-08
 */
@MyBatisDao
public interface TerBillMonthDao extends CrudDao<TerBillMonth> {
	
}