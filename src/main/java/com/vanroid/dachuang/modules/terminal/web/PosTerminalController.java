/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;

/**
 * POS终端Controller
 *
 * @author CGZ
 * @version 2016-10-26
 */
@Controller
@RequestMapping(value = "${adminPath}/terminal/posTerminal")
public class PosTerminalController extends BaseController {

    @Autowired
    private PosTerminalService posTerminalService;

    @ModelAttribute
    public PosTerminal get(@RequestParam(required = false) String id) {
        PosTerminal entity = null;
        if (StringUtils.isNotBlank(id)) {
            entity = posTerminalService.get(id);
        }
        if (entity == null) {
            entity = new PosTerminal();
        }
        return entity;
    }

    @RequiresPermissions("terminal:posTerminal:view")
    @RequestMapping(value = {"list", ""})
    public String list(PosTerminal posTerminal, HttpServletRequest request, HttpServletResponse response, Model model) {
        //Page<PosTerminal> page = posTerminalService.findPage(new Page<PosTerminal>(request, response), posTerminal);
        Page<PosTerminal> page = posTerminalService.findPageByUser(new Page<PosTerminal>(request, response), posTerminal);

        model.addAttribute("page", page);
        return "modules/terminal/posTerminalList";
    }

    @RequiresPermissions("terminal:posTerminal:view")
    @RequestMapping(value = "form")
    public String form(PosTerminal posTerminal, Model model) {
        model.addAttribute("posTerminal", posTerminal);
        return "modules/terminal/posTerminalForm";
    }

    @RequiresPermissions("terminal:posTerminal:edit")
    @RequestMapping(value = "save")
    public String save(PosTerminal posTerminal, Model model, RedirectAttributes redirectAttributes) {
        if (!beanValidator(model, posTerminal)) {
            return form(posTerminal, model);
        }
        posTerminalService.save(posTerminal);
        addMessage(redirectAttributes, "保存POS终端成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/posTerminal/?repage";
    }

    @RequiresPermissions("terminal:posTerminal:edit")
    @RequestMapping(value = "delete")
    public String delete(PosTerminal posTerminal, RedirectAttributes redirectAttributes) {
        posTerminalService.delete(posTerminal);
        addMessage(redirectAttributes, "删除POS终端成功");
        return "redirect:" + Global.getAdminPath() + "/terminal/posTerminal/?repage";
    }

}