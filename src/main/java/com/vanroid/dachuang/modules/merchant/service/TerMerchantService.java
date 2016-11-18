/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.service;

import java.util.List;

import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
 * @version 2016-11-16
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

    @Transactional
    public List<String> findMerchantNumsByUser() {
        User user = UserUtils.getUser();
        if (user.getCompany().getId().equals(Global.getDCCompanyId())) {   // 根部门可以看所有终端
            logger.debug("总公司用户{}登录，不需要检查终端数", user.getName());
            return Lists.newArrayList();
        }
        return findMerchantNumsByUser(user);
    }

    @Transactional
    private List<String> findMerchantNumsByUser(User user) {
        return dao.findMerchantNumListByOffice(user.getCompany());
    }
}