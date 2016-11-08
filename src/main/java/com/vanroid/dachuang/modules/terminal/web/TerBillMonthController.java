/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.vanroid.dachuang.modules.terminal.entity.TerBillMonth;
import com.vanroid.dachuang.modules.terminal.service.TerBillMonthService;

/**
 * 月帐单Controller
 * @author CGZ
 * @version 2016-11-08
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/terBillMonth")
public class TerBillMonthController extends BaseController {

	@Autowired
	private TerBillMonthService terBillMonthService;
	
	@ModelAttribute
	public TerBillMonth get(@RequestParam(required=false) String id) {
		TerBillMonth entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = terBillMonthService.get(id);
		}
		if (entity == null){
			entity = new TerBillMonth();
		}
		return entity;
	}
	
	@RequiresPermissions("terminal:terBillMonth:view")
	@RequestMapping(value = {"list", ""})
	public String list(TerBillMonth terBillMonth, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TerBillMonth> page = terBillMonthService.findPage(new Page<TerBillMonth>(request, response), terBillMonth); 
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
		if (!beanValidator(model, terBillMonth)){
			return form(terBillMonth, model);
		}
		terBillMonthService.save(terBillMonth);
		addMessage(redirectAttributes, "保存月帐单成功");
		return "redirect:"+Global.getAdminPath()+"/terminal/terBillMonth/?repage";
	}
	
	@RequiresPermissions("terminal:terBillMonth:edit")
	@RequestMapping(value = "delete")
	public String delete(TerBillMonth terBillMonth, RedirectAttributes redirectAttributes) {
		terBillMonthService.delete(terBillMonth);
		addMessage(redirectAttributes, "删除月帐单成功");
		return "redirect:"+Global.getAdminPath()+"/terminal/terBillMonth/?repage";
	}

}