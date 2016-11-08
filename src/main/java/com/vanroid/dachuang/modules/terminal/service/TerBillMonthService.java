/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import com.vanroid.dachuang.modules.terminal.dao.TerBillMonthDao;

/**
 * 月帐单Service
 * @author CGZ
 * @version 2016-11-08
 */
@Service
@Transactional(readOnly = true)
public class TerBillMonthService extends CrudService<TerBillMonthDao, TerBillMonth> {

	public TerBillMonth get(String id) {
		return super.get(id);
	}
	
	public List<TerBillMonth> findList(TerBillMonth terBillMonth) {
		return super.findList(terBillMonth);
	}
	
	public Page<TerBillMonth> findPage(Page<TerBillMonth> page, TerBillMonth terBillMonth) {
		return super.findPage(page, terBillMonth);
	}
	
	@Transactional(readOnly = false)
	public void save(TerBillMonth terBillMonth) {
		super.save(terBillMonth);
	}
	
	@Transactional(readOnly = false)
	public void delete(TerBillMonth terBillMonth) {
		super.delete(terBillMonth);
	}
	
}