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
     *
     * @param office
     * @return
     */
    List<String> findIdsByOffice(Office office);

    /**
     * 根据给定id加载实体列表
     *
     * @param map
     * @return
     */

    List<PosTerminal> findListByIds(Map<String, Object> map);

    /**
     * 根据给定id加载实体列表,获取结果数量
     *
     * @param map
     * @return
     */
    int findListByIdsCount(Map<String, Object> map);

    /**
     * 查找终端号列表
     *
     * @return
     */
    List<String> findTerNumList();

    /**
     * 批量插入
     *
     * @param posTerminals
     * @return
     */
    int batchInsert(List<PosTerminal> posTerminals);
}