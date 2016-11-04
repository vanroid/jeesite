/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 消费日流水DAO接口
 *
 * @author CGZ
 * @version 2016-10-30
 */
@MyBatisDao
public interface TerBillDayDao extends CrudDao<TerBillDay> {

    int batchInsert(List<TerBillDay> terBillDays);

    List<TerBillDay> findListByTerIds(Map params);

    /**
     * 通过terminal的id列表查找所有的记录数
     *
     * @param terIds
     * @return
     */

    int countByTerIds(List<String> terIds);

    /**
     * 通过清算日期删除所有记录
     *
     * @param clearDate
     * @return
     */
    int deleteByClearDate(Date clearDate);
}