package com.thinkgem.jeesite.modules.terminal.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.terminal.entity.Bill;

import java.util.List;

/**
 * Created by cgz on 16-10-25.
 */
@MyBatisDao
public interface BillDao extends CrudDao<Bill> {
    List<Bill> findByTerminalIdIn(String[] ids);
}
