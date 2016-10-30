/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;
import com.vanroid.dachuang.modules.terminal.dao.TerBillDayDao;

/**
 * 消费日流水Service
 * @author CGZ
 * @version 2016-10-30
 */
@Service
@Transactional(readOnly = true)
public class TerBillDayService extends CrudService<TerBillDayDao, TerBillDay> {

	public TerBillDay get(String id) {
		return super.get(id);
	}
	
	public List<TerBillDay> findList(TerBillDay terBillDay) {
		return super.findList(terBillDay);
	}
	
	public Page<TerBillDay> findPage(Page<TerBillDay> page, TerBillDay terBillDay) {
		return super.findPage(page, terBillDay);
	}
	
	@Transactional(readOnly = false)
	public void save(TerBillDay terBillDay) {
		super.save(terBillDay);
	}
	
	@Transactional(readOnly = false)
	public void delete(TerBillDay terBillDay) {
		super.delete(terBillDay);
	}
	
}