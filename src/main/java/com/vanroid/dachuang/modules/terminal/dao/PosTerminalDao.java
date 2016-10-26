/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;

/**
 * 终端设备DAO接口
 * @author CGZ
 * @version 2016-10-25
 */
@MyBatisDao
public interface PosTerminalDao extends CrudDao<PosTerminal> {
	
}