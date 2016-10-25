package com.thinkgem.jeesite.modules.terminal.service;

import com.google.common.collect.Maps;
import com.sun.tools.corba.se.idl.constExpr.Terminal;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.thinkgem.jeesite.modules.terminal.dao.BillDao;
import com.thinkgem.jeesite.modules.terminal.dao.TerminalDao;
import com.thinkgem.jeesite.modules.terminal.entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by cgz on 16-10-25.
 */
@Service
public class TerminalService extends CrudService<TerminalDao, Terminal> {

    @Autowired
    private TerminalDao terminalDao;

    @Autowired
    private BillDao billDao;

    /**
     * 查看当前用户所有的终端帐单
     */
    public Map<String, Object> listAllData(Date startTime, Date endTime) {
        User user = UserUtils.getUser();
        if (user == null) return null;

        return listAllData(user, startTime, endTime);
    }

    public Map<String, Object> listAllData(User user, Date startTime, Date endTime) {

        List<String> terminalIds = UserUtils.getAllTermialIds(user);

        findBillsByTerminalIds(terminalIds);

        Map map = Maps.newHashMap();
        return map;
    }

    /**
     * 根据终端id查找帐单数据
     *
     * @param terminalIds
     */
    private List<Bill> findBillsByTerminalIds(List<String> terminalIds) {
        String[] idsArr = new String[terminalIds.size()];
        idsArr = terminalIds.toArray(idsArr);
        return billDao.findByTerminalIdIn(idsArr);
    }


}
