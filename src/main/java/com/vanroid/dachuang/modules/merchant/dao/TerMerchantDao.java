/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.vanroid.dachuang.modules.merchant.entity.TerMerchant;

import java.util.List;

/**
 * 商户管理DAO接口
 * @author CGZ
 * @version 2016-11-16
 */
@MyBatisDao
public interface TerMerchantDao extends CrudDao<TerMerchant> {

    List<String> findMerchantNumList();
}