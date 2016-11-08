/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.common.DaChuangUtils;
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
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import com.vanroid.dachuang.modules.terminal.service.PosTerminalService;

import java.util.Map;

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

    @RequiresPermissions("terminal:posTerminal:edit")
    @RequestMapping(value = "import", method = RequestMethod.GET)
    public String importTerminalsAndUsersPage(RedirectAttributes redirectAttributes) {
        return "modules/terminal/importTerminals";
    }

    @RequiresPermissions("terminal:posTerminal:edit")
    @RequestMapping(value = "import", method = RequestMethod.POST)
    public String importTerminalsAndUsers(MultipartFile file, RedirectAttributes redirectAttributes) {
        logger.debug("文件成功上传：{}", file.getName());
        Map<String, Object> result = posTerminalService.importTerminals(file);
        if (result == null) { //插入失败
            addMessage(redirectAttributes, "导入出错，请联系系统管理员");
        } else {
            addMessage(redirectAttributes, result.get(StatusConstants.SERVICE_RESULT_MESSAGE).toString());
        }

        return "redirect:" + Global.getAdminPath() + "/terminal/posTerminal/?repage";
    }

    @RequiresPermissions("terminal:posTerminal:view")
    @RequestMapping(value = "export", method = RequestMethod.POST)
    public String exportTerminalsAndUsers(HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {

        try {
            User user = UserUtils.getUser();
            logger.debug("用户{}正在导出终端数据", user.getName());

            String fileName = "终端数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

            PosTerminal posTerminal = new PosTerminal();
            Page<PosTerminal> posTerminalPage = new Page<PosTerminal>();
            posTerminalPage.setPageSize(-1);

            Page<PosTerminal> page = posTerminalService.findPageByUser(posTerminalPage, posTerminal);

            new ExportExcel("终端数据", PosTerminal.class).setDataList(page.getList()).write(response, fileName).dispose();
            logger.debug("用户{}正在导出终端数据{}条", user.getName(), page.getList().size());
            return null;
        } catch (Exception e) {
            logger.error("导出数据出错", e);
            addMessage(redirectAttributes, "导出数据出错：{}", e.getMessage());
        }
        return "redirect:" + Global.getAdminPath() + "/terminal/posTerminal/?repage";
    }

}