/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.service;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;
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
 *
 * @author CGZ
 * @version 2016-11-03
 */
@Service
@Transactional(readOnly = true)
public class TerMerchantService extends CrudService<TerMerchantDao, TerMerchant> {

    @Autowired
    private PosTerminalService posTerminalService;

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

    @Transactional
    public Page<TerMerchant> findPageByUser(Page<TerMerchant> page, TerMerchant terMerchant) {
        terMerchant.setPage(page);
        // 1.查找当前用户所有的terminalId
        List<String> terIds = posTerminalService.findIdsByUser();

        Map params = Maps.newHashMap();
        params.put("page", page);
        params.put("merchant", terMerchant);
        params.put("list", terIds);
        params.put("dbName", terMerchant.getDbName());
        int rowCnt = dao.countByTerIds(params);
        page.setCount(rowCnt);
        page.setList(dao.findListByTerIds(params));
        logger.debug("找到所属用户{}符合查询条件的商户记录数：{}", UserUtils.getUser().getName(), rowCnt);
        return page;
    }

    @Transactional(readOnly = false)
    public int insertMerchantFromTerminal() {
        return dao.insertMerchantFromTerminal();
    }
}