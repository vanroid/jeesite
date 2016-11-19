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
import com.vanroid.dachuang.modules.terminal.entity.TerTerminal;
import com.vanroid.dachuang.modules.terminal.service.TerTerminalService;

/**
 * 终端信息Controller
 *
 * @author CGZ
 * @version 2016-11-17
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/terTerminal")
public class TerTerminalController extends BaseController {

    @Autowired
    private TerTerminalService terTerminalService;

    @ModelAttribute
    public TerTerminal get(@RequestParam(required = false) String id) {
        TerTerminal entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = terTerminalService.get(id);
        }
        if (entity == null) {
            entity = new TerTerminal();
        }
        return entity;
    }

    @RequiresPermissions("terminal:terTerminal:view")
    @RequestMapping(value = {"list", ""})
    public String list(TerTerminal terTerminal, HttpServletRequest request, HttpServletResponse response, Model model) {
        Page<TerTerminal> page = terTerminalService.findPage(new Page<TerTerminal>(request, response), terTerminal);
        model.addAttribute("page", page);
        return "modules/terminal/terTerminalList";
    }

    @RequiresPermissions("terminal:terTerminal:view")
    @RequestMapping(value = "form")
    public String form(TerTerminal terTerminal, Model model) {
        model.addAttribute("terTerminal", terTerminal);
        return "modules/terminal/terTerminalForm";
    }

    @RequiresPermissions("terminal:terTerminal:edit")
    @RequestMapping(value = "save")
    public String save(TerTerminal terTerminal, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, terTerminal)) {
            return form(terTerminal, model);
        }
        terTerminalService.save(terTerminal);
        addMessage(redirectAttributes, "保存终端信息成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/terTerminal/?repage";
    }

    @RequiresPermissions("terminal:terTerminal:edit")
    @RequestMapping(value = "delete")
    public String delete(TerTerminal terTerminal, RedirectAttributes redirectAttributes) {
        terTerminalService.delete(terTerminal);
        addMessage(redirectAttributes, "删除终端信息成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/terTerminal/?repage";
    }

}