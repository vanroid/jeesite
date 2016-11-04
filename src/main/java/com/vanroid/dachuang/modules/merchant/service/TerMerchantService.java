/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.vanroid.dachuang.modules.merchant.entity.TerMerchant;
import com.vanroid.dachuang.modules.merchant.dao.TerMerchantDao;

/**
 * 商户管理Service
 * @author CGZ
 * @version 2016-11-03
 */
@Service
@Transactional(readOnly = true)
public class TerMerchantService extends CrudService<TerMerchantDao, TerMerchant> {

	
	public TerMerchant get(String id) {
		TerMerchant terMerchant = super.get(id);
		return terMerchant;
	}
	
	public List<TerMerchant> findList(TerMerchant terMerchant) {
		return super.findList(terMerchant);
	}
	
	public Page<TerMerchant> findPage(Page<TerMerchant> page, TerMerchant terMerchant) {
		return super.findPage(page, terMerchant);
	}
	
	@Transactional(readOnly = false)
	public void save(TerMerchant terMerchant) {
		super.save(terMerchant);
	}
	
	@Transactional(readOnly = false)
	public void delete(TerMerchant terMerchant) {
		super.delete(terMerchant);
	}
	
}