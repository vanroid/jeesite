/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;

/**
 * POS终端DAO接口
 * @author CGZ
 * @version 2016-10-26
 */
@MyBatisDao
public interface PosTerminalDao extends CrudDao<PosTerminal> {
	
}