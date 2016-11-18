/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.TerTerminal;

import java.util.List;

/**
 * 终端信息DAO接口
 * @author CGZ
 * @version 2016-11-17
 */
@MyBatisDao
public interface TerTerminalDao extends CrudDao<TerTerminal> {

    int batchInsert(List<TerTerminal> terTerminals);

    List<String> findTerNumList();
}