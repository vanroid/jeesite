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
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;
import com.vanroid.dachuang.modules.terminal.service.TerBillDayService;

/**
 * 消费日流水Controller
 * @author CGZ
 * @version 2016-10-30
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/terBillDay")
public class TerBillDayController extends BaseController {

	@Autowired
	private TerBillDayService terBillDayService;
	
	@ModelAttribute
	public TerBillDay get(@RequestParam(required=false) String id) {
		TerBillDay entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = terBillDayService.get(id);
		}
		if (entity == null){
			entity = new TerBillDay();
		}
		return entity;
	}
	
	@RequiresPermissions("terminal:terBillDay:view")
	@RequestMapping(value = {"list", ""})
	public String list(TerBillDay terBillDay, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TerBillDay> page = terBillDayService.findPage(new Page<TerBillDay>(request, response), terBillDay); 
		model.addAttribute("page", page);
		return "modules/terminal/terBillDayList";
	}

	@RequiresPermissions("terminal:terBillDay:view")
	@RequestMapping(value = "form")
	public String form(TerBillDay terBillDay, Model model) {
		model.addAttribute("terBillDay", terBillDay);
		return "modules/terminal/terBillDayForm";
	}

	@RequiresPermissions("terminal:terBillDay:edit")
	@RequestMapping(value = "save")
	public String save(TerBillDay terBillDay, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, terBillDay)){
			return form(terBillDay, model);
		}
		terBillDayService.save(terBillDay);
		addMessage(redirectAttributes, "保存消费日流水成功");
		return "redirect:"+Global.getAdminPath()+"/terminal/terBillDay/?repage";
	}
	
	@RequiresPermissions("terminal:terBillDay:edit")
	@RequestMapping(value = "delete")
	public String delete(TerBillDay terBillDay, RedirectAttributes redirectAttributes) {
		terBillDayService.delete(terBillDay);
		addMessage(redirectAttributes, "删除消费日流水成功");
		return "redirect:"+Global.getAdminPath()+"/terminal/terBillDay/?repage";
	}

}