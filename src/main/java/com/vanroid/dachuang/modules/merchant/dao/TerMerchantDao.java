/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.merchant.entity.TerMerchant;

import java.util.List;
import java.util.Map;

/**
 * 商户管理DAO接口
 *
 * @author CGZ
 * @version 2016-11-03
 */
@MyBatisDao
public interface TerMerchantDao extends CrudDao<TerMerchant> {

    /**
     * 通过terminalID查找商户
     *
     * @param params
     * @return
     */
    List<TerMerchant> findListByTerIds(Map params);

    int countByTerIds(Map params);

    int insertMerchantFromTerminal();
}