/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;

import java.util.List;
import java.util.Map;

/**
 * 终端设备DAO接口
 *
 * @author CGZ
 * @version 2016-10-26
 */
@MyBatisDao
public interface PosTerminalDao extends CrudDao<PosTerminal> {

    /**
     * 查找某机构下的所有终端id
     * @param office
     * @return
     */
    List<String> findIdsByOffice(Office office);

    /**
     * 根据给定id加载实体列表
     * @param ids
     * @param posTerminal
     * @return
     */

    List<PosTerminal> findListByIds(Map<String, Object> map);
}