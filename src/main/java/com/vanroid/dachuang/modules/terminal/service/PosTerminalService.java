/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Area;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.Role;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import com.vanroid.dachuang.common.DaChuangUtils;
import com.vanroid.dachuang.common.ExcelUtils;
import com.vanroid.dachuang.common.StatusConstants;
import com.vanroid.dachuang.modules.terminal.dao.PosTerminalDao;
import com.vanroid.dachuang.modules.terminal.entity.Bill;
import com.vanroid.dachuang.modules.terminal.entity.PosTerminal;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * POS终端Service
 *
 * @author CGZ
 * @version 2016-10-26
 */
@Service
@Transactional(readOnly = true)
public class PosTerminalService extends CrudService<PosTerminalDao, PosTerminal> {

    @Autowired
    private OfficeService officeService;

    @Autowired
    private SystemService systemService;

    public PosTerminal get(String id) {
        PosTerminal posTerminal = super.get(id);
        return posTerminal;
    }

    public List<PosTerminal> findList(PosTerminal posTerminal) {
        return super.findList(posTerminal);
    }

    public Page<PosTerminal> findPage(Page<PosTerminal> page, PosTerminal posTerminal) {
        return super.findPage(page, posTerminal);
    }

    @Transactional(readOnly = false)
    public void save(PosTerminal posTerminal) {
        super.save(posTerminal);
    }

    @Transactional(readOnly = false)
    public void delete(PosTerminal posTerminal) {
        super.delete(posTerminal);
    }


    /**
     * 查看当前用户所有的终端帐单
     */
    public Map<String, Object> listAllData(Date startTime, Date endTime) {
        User user = UserUtils.getUser();
        if (user == null) return null;

        return listAllData(user, startTime, endTime);
    }

    public Map<String, Object> listAllData(User user, Date startTime, Date endTime) {

        List<String> terminalIds = UserUtils.getAllTermialIds(user);

        findBillsByTerminalIds(terminalIds);

        Map map = Maps.newHashMap();
        return map;
    }

    /**
     * 通过excel文件导入终端信息,机构也是通过此文件导入
     *
     * @param fileName 文件路径
     * @return 增加终端数
     */
    @Transactional(readOnly = false)
    public int importTerminals(String fileName) {
        logger.debug("开始导入终端");
        int terminalCnt = 0;

        List<User> users = Lists.newArrayList();

        Map<String, List<PosTerminal>> userMap = Maps.newHashMap();

        try {
            // 第二个参数已无作用
            ImportExcel importExcel = new ImportExcel(fileName, 0);

            int rows = importExcel.getLastDataRowNum();
            // 遍历每一行,收集 以登录名为key,其他数据为value的map
            for (int i = 1; i < rows; i++) {
                Row row = importExcel.getRow(i);

                // 没有[商户号][终端号][后台账户]的视为无效记录,停止往下执行
                if (ExcelUtils.cellIsBank(row.getCell(6)) || ExcelUtils.cellIsBank(row.getCell(7)) || ExcelUtils.cellIsBank(row.getCell(24))) {
                    logger.debug("导入信息时发现必需字段缺失，行数：{}", i);
                    break;
                }

                String loginName = row.getCell(24).getStringCellValue();
                // 获取此用户下的所有终端
                List<PosTerminal> userPosTerminals = userMap.get(loginName);
                if (userPosTerminals == null) {
                    userPosTerminals = Lists.newArrayList();
                }
                PosTerminal posTerminal = new PosTerminal();
                // 为字段赋值
                try {
                    excelRowToPosterminal(posTerminal, row);
                }catch (Exception e){
                    logger.error("字段中存在错误值，行数：{}",i);
                    throw new RuntimeException(e);
                }
                userPosTerminals.add(posTerminal);

                userMap.put(loginName, userPosTerminals);

                terminalCnt++;
            }

            int userCnt = 0;

            // 插入机构\部门\用户
            Set<String> userLoginNames = userMap.keySet();
            logger.debug("检测到用户数量：{}", userLoginNames.size());

            // 获取根机构
            Office rootOffice = new Office();
            rootOffice.setId(Global.getRootOfficeId());
            Area rootArea = new Area();
            rootArea.setId(Global.getRootAreaId());
            // 操作用户
            User curUser = UserUtils.getUser();
            if (curUser == null || StringUtils.isBlank(curUser.getId())) { // 使用默认导入操作
                curUser = UserUtils.get(Global.getDefaultUserId());
            }
            logger.debug("正在操作操作的用户是:{}:{}", curUser.getId(), curUser.getName());
            for (String loginName : userLoginNames) {

                // 机构
                Office company = new Office();
                company.setName(loginName);
                company.setType(StatusConstants.OFFICE_TYPE_COMPANY);
                company.setArea(rootArea);
                company.setCreateBy(curUser);
                company.setUpdateBy(curUser);
                company.setUseable(StatusConstants.OFFICE_USEABLE_ENABLE);
                // 所有导入的机构都是在[代理商之下]
                company.setParent(rootOffice);


                // todo 测试标识
                company.setAddress("test");
                officeService.save(company);

                // 部门
                Office office = new Office();
                office.setName(StatusConstants.OFFICE_DEFULT_OFFICE_NAME);
                office.setParent(company);
                office.setType(StatusConstants.OFFICE_TYPE_OFFICE);
                office.setArea(rootArea);
                office.setCreateBy(curUser);
                office.setUpdateBy(curUser);
                office.setUseable(StatusConstants.OFFICE_USEABLE_ENABLE);
                // todo 测试标识
                office.setAddress("test");
                officeService.save(office);

                // 用户
                User user = new User();
                user.setLoginName(loginName);
                user.setCompany(company);
                user.setOffice(office);
                user.setCreateBy(curUser);
                user.setUpdateBy(curUser);
                user.setPassword(SystemService.entryptPassword(StatusConstants.USER_DEFAULT_PASSWORD));
                user.setName(loginName);
                user.setRemarks(StatusConstants.USER_DEFAULT_REMARKS);
                // 代理商角色
                List<Role> roles = Lists.newArrayList();
                roles.add(new Role(Global.getAgentRoleId()));
                user.setRoleList(roles);


                systemService.saveUser(user);

                // 插入终端
                List<PosTerminal> terminals = userMap.get(loginName);
                for (PosTerminal posTerminal : terminals) {
                    // 后台编号,所属用户
                    posTerminal.setUser(user);
                    posTerminal.setCreateBy(curUser);
                    posTerminal.setUpdateBy(curUser);
                    // todo 测试标识
                    posTerminal.setRemarks(StatusConstants.TERMINAL_DEFAULT_REMARKS);
                    this.save(posTerminal);
                }
                logger.debug("共导入终端数:" + terminalCnt);
                userCnt++;
            }
            logger.debug("共导入用户数：" + userCnt);


        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return terminalCnt;
    }

    private void excelRowToPosterminal(PosTerminal posTerminal, Row row) {
        // 进件日期
        posTerminal.setImportDate(ExcelUtils.getDateCellValue(row, 0));
        //下机日期
        posTerminal.setDownDate(ExcelUtils.getDateCellValue(row, 1));
        // 装机日期
        posTerminal.setInstallDate(ExcelUtils.getDateCellValue(row, 2));
        // 交件支行
        posTerminal.setTransBank(ExcelUtils.getStringCellValue(row, 3));
        // 机身号
        posTerminal.setDeviceNum(ExcelUtils.getStringCellValue(row, 4));
        // 机子型号
        posTerminal.setDeviceType(ExcelUtils.getStringCellValue(row, 5));
        // 商户号
        posTerminal.setMerchantNum(ExcelUtils.getStringCellValue(row, 6));
        // 终端号
        posTerminal.setTermialNum(ExcelUtils.getStringCellValue(row, 7));
        // 微信二维码
        posTerminal.setWechatUrl(ExcelUtils.getStringCellValue(row, 8));
        // 营业执照号码
        posTerminal.setBusinessLicense(ExcelUtils.getStringCellValue(row, 9));
        // 商户名称
        posTerminal.setMerchantName(ExcelUtils.getStringCellValue(row, 10));
        // 地址
        posTerminal.setMerchantAddress(ExcelUtils.getStringCellValue(row, 11));
        // 法人
        posTerminal.setMerchantLegalPerson(ExcelUtils.getStringCellValue(row, 12));
        // 入账人
        posTerminal.setBookingPerson(ExcelUtils.getStringCellValue(row, 13));
        // 联系电话
        posTerminal.setTelphone(ExcelUtils.getStringCellValue(row, 14));
        // 装机电话
        posTerminal.setInstallPhone(ExcelUtils.getStringCellValue(row, 15));
        // MCC码
        posTerminal.setDeviceMcc(ExcelUtils.getStringCellValue(row, 16));
        // 借记卡费率
        posTerminal.setDebitRate(ExcelUtils.getStringCellValue(row, 17));
        // 贷记卡费率
        posTerminal.setCreditRate(ExcelUtils.getStringCellValue(row, 18));
        // 外币卡费率
        posTerminal.setForeignRate(ExcelUtils.getStringCellValue(row, 19));
        // 机具类型
        posTerminal.setMachineType(ExcelUtils.getStringCellValue(row, 20));
        // 身份证号码
        posTerminal.setIdCard(ExcelUtils.getStringCellValue(row, 21));
        // 银行卡
        posTerminal.setBankCard(ExcelUtils.getStringCellValue(row, 22));
        // 银行卡开户行
        posTerminal.setBankCardAccountBank(ExcelUtils.getStringCellValue(row, 23));

        // 业务员
        posTerminal.setSalesman(ExcelUtils.getStringCellValue(row, 25));
        // 详情
        posTerminal.setTerminalDesc(ExcelUtils.getStringCellValue(row, 26));


    }

    /**
     * 根据终端id查找帐单数据
     *
     * @param terminalIds
     */
    public List<Bill> findBillsByTerminalIds(List<String> terminalIds) {
        String[] idsArr = new String[terminalIds.size()];
        idsArr = terminalIds.toArray(idsArr);
        // todo return billDao.findByTerminalIdIn(idsArr);
        return null;
    }
}