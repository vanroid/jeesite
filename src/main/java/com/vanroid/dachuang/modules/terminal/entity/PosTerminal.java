/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.entity;

import org.hibernate.validator.constraints.Length;
import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 终端设备Entity
 * @author CGZ
 * @version 2016-10-25
 */
public class PosTerminal extends DataEntity<PosTerminal> {
	
	private static final long serialVersionUID = 1L;
	private String terminalNum;		// 终端编号
	private User user;		// 所属用户
	
	public PosTerminal() {
		super();
	}

	public PosTerminal(String id){
		super(id);
	}

	@Length(min=1, max=15, message="终端编号长度必须介于 1 和 15 之间")
	public String getTerminalNum() {
		return terminalNum;
	}

	public void setTerminalNum(String terminalNum) {
		this.terminalNum = terminalNum;
	}
	
	@NotNull(message="所属用户不能为空")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}