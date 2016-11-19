/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.web;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.BaseService;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.common.web.BaseController;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import com.vanroid.dachuang.modules.terminal.service.TerBillMonthService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 月帐单Controller
 *
 * @author CGZ
 * @version 2016-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/terBillMonth")
public class TerBillMonthController extends BaseController {

    @Autowired
    private TerBillMonthService terBillMonthService;

    @ModelAttribute
    public TerBillMonth get(@RequestParam(required = false) String id) {
        TerBillMonth entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = terBillMonthService.get(id);
        }
        if (entity == null) {
            entity = new TerBillMonth();
        }
        return entity;
    }

    @RequiresPermissions("terminal:terBillMonth:view")
    @RequestMapping(value = {"list", ""})
    public String list(TerBillMonth terBillMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
        terBillMonth.getSqlMap().put("dsf", BaseService.dataScopeFilter(terBillMonth.getCurrentUser(), "o", ""));
        Page<TerBillMonth> page = terBillMonthService.findPage(new Page<TerBillMonth>(request, response), terBillMonth);
        //Page<TerBillMonth> page = terBillMonthService.findPage(new Page<TerBillMonth>(request, response), terBillMonth);
        model.addAttribute("page", page);
        return "modules/terminal/terBillMonthList";
    }

    @RequiresPermissions("terminal:terBillMonth:view")
    @RequestMapping(value = "form")
    public String form(TerBillMonth terBillMonth, Model model) {
        model.addAttribute("terBillMonth", terBillMonth);
        return "modules/terminal/terBillMonthForm";
    }

    @RequiresPermissions("terminal:terBillMonth:edit")
    @RequestMapping(value = "save")
    public String save(TerBillMonth terBillMonth, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, terBillMonth)) {
            return form(terBillMonth, model);
        }
        terBillMonthService.save(terBillMonth);
        addMessage(redirectAttributes, "保存月帐单成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/terBillMonth/?repage";
    }

    @RequiresPermissions("terminal:terBillMonth:edit")
    @RequestMapping(value = "delete")
    public String delete(TerBillMonth terBillMonth, RedirectAttributes redirectAttributes) {
        terBillMonthService.delete(terBillMonth);
        addMessage(redirectAttributes, "删除月帐单成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/terBillMonth/?repage";
    }

    @RequiresPermissions("terminal:terBillMonth:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importBillMonth(MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            Map<String, Object> result = terBillMonthService.importBillMonths(file);
            addMessage(redirectAttributes, "导入帐单成功", result.get("message").toString());
        } catch (Exception e) {
            logger.error("导入帐单出错", e);
            addMessage(redirectAttributes, "导入帐单出错", e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/terminal/terBillMonth/?repage";
    }

    @RequiresPermissions("terminal:terBillMonth:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportFile(TerBillMonth terBillMonth, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
        try {
            terBillMonth.getSqlMap().put("dsf", BaseService.dataScopeFilter(terBillMonth.getCurrentUser(), "o", ""));

            List<TerBillMonth> list = terBillMonthService.findList(terBillMonth);

            String fileName = "月交易数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
            new ExportExcel("月交易数据", TerBillMonth.class).setDataList(list).write(response, fileName).dispose();
            return null;
        } catch (Exception e) {
            addMessage(redirectAttributes, "导出帐单失败！失败信息：" + e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/terminal/terBillMonth/?repage";
    }


}