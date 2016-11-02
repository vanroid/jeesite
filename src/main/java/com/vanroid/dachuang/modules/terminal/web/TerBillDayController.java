/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vanroid.dachuang.common.StatusConstants;
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

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.vanroid.dachuang.modules.terminal.entity.TerBillDay;
import com.vanroid.dachuang.modules.terminal.service.TerBillDayService;

import java.util.Map;

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
		//Page<TerBillDay> page = terBillDayService.findPage(new Page<TerBillDay>(request, response), terBillDay);
		Page<TerBillDay> page = terBillDayService.findPageByUser(new Page<TerBillDay>(request, response), terBillDay);
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

	@RequiresPermissions("terminal:terBillDay:import")
	@RequestMapping(value = "import", method = RequestMethod.GET)
	public String importBillDayPage(RedirectAttributes redirectAttributes) {
		return "modules/terminal/importBillDays";
	}

	@RequiresPermissions("terminal:terBillDay:import")
	@RequestMapping(value = "import", method = RequestMethod.POST)
	public String importBillDays(MultipartFile file, RedirectAttributes redirectAttributes) {
		logger.debug("文件成功上传：{}", file.getName());
		Map<String, Object> result = terBillDayService.importBillDays(file);
		if (result == null) { //插入失败
			addMessage(redirectAttributes, "导入出错，请联系系统管理员");
		} else {
			addMessage(redirectAttributes, result.get(StatusConstants.SERVICE_RESULT_MESSAGE).toString());
		}

		return "redirect:" + Global.getAdminPath() + "/terminal/posTerminal/?repage";
	}

}