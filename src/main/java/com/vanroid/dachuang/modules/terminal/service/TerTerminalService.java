/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.vanroid.dachuang.modules.terminal.entity.TerTerminal;
import com.vanroid.dachuang.modules.terminal.dao.TerTerminalDao;

/**
 * 终端信息Service
 * @author CGZ
 * @version 2016-11-17
 */
@Service
@Transactional(readOnly = true)
public class TerTerminalService extends CrudService<TerTerminalDao, TerTerminal> {

	
	public TerTerminal get(String id) {
		TerTerminal terTerminal = super.get(id);
		return terTerminal;
	}
	
	public List<TerTerminal> findList(TerTerminal terTerminal) {
		return super.findList(terTerminal);
	}
	
	public Page<TerTerminal> findPage(Page<TerTerminal> page, TerTerminal terTerminal) {
		return super.findPage(page, terTerminal);
	}
	
	@Transactional(readOnly = false)
	public void save(TerTerminal terTerminal) {
		super.save(terTerminal);
	}
	
	@Transactional(readOnly = false)
	public void delete(TerTerminal terTerminal) {
		super.delete(terTerminal);
	}
	
}