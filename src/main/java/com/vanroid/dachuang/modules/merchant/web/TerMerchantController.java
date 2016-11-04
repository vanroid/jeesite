/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.merchant.web;

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
import com.vanroid.dachuang.modules.merchant.entity.TerMerchant;
import com.vanroid.dachuang.modules.merchant.service.TerMerchantService;

/**
 * 商户管理Controller
 * @author CGZ
 * @version 2016-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/merchant/terMerchant")
public class TerMerchantController extends BaseController {

	@Autowired
	private TerMerchantService terMerchantService;
	
	@ModelAttribute
	public TerMerchant get(@RequestParam(required=false) String id) {
		TerMerchant entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = terMerchantService.get(id);
		}
		if (entity == null){
			entity = new TerMerchant();
		}
		return entity;
	}
	
	@RequiresPermissions("merchant:terMerchant:view")
	@RequestMapping(value = {"list", ""})
	public String list(TerMerchant terMerchant, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TerMerchant> page = terMerchantService.findPage(new Page<TerMerchant>(request, response), terMerchant); 
		model.addAttribute("page", page);
		return "modules/merchant/terMerchantList";
	}

	@RequiresPermissions("merchant:terMerchant:view")
	@RequestMapping(value = "form")
	public String form(TerMerchant terMerchant, Model model) {
		model.addAttribute("terMerchant", terMerchant);
		return "modules/merchant/terMerchantForm";
	}

	@RequiresPermissions("merchant:terMerchant:edit")
	@RequestMapping(value = "save")
	public String save(TerMerchant terMerchant, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, terMerchant)){
			return form(terMerchant, model);
		}
		terMerchantService.save(terMerchant);
		addMessage(redirectAttributes, "保存商户成功");
		return "redirect:"+Global.getAdminPath()+"/merchant/terMerchant/?repage";
	}
	
	@RequiresPermissions("merchant:terMerchant:edit")
	@RequestMapping(value = "delete")
	public String delete(TerMerchant terMerchant, RedirectAttributes redirectAttributes) {
		terMerchantService.delete(terMerchant);
		addMessage(redirectAttributes, "删除商户成功");
		return "redirect:"+Global.getAdminPath()+"/merchant/terMerchant/?repage";
	}

}