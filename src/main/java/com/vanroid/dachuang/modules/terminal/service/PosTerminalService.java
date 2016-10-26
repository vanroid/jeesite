/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.vanroid.dachuang.modules.terminal.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.excel.ImportExcel;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
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
    SystemService systemService;

    @Autowired
    OfficeService officeService;


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
    public int importTerminals(String fileName) {
        List<User> users = Lists.newArrayList();

        Map<String, List<PosTerminal>> userMap = Maps.newHashMap();

        try {
            ImportExcel importExcel = new ImportExcel("/home/cgz/win7vm/大创电子---商户详情表.xlsx", 27);

            //importExcel.getDataList(PosTerminal.class);

            int rows = importExcel.getLastDataRowNum();
            // 遍历每一行,收集 以登录名为key,其他数据为value的map
            for (int i = 1; i < rows; i++) {
                Row row = importExcel.getRow(i);
                String loginName = row.getCell(24).getStringCellValue();
                // 获取此用户下的所有终端
                List<PosTerminal> userPosTerminals = userMap.get(loginName);
                if (userPosTerminals == null) {
                    userPosTerminals = Lists.newArrayList();
                }
                PosTerminal posTerminal = new PosTerminal();
                // todo other info
                // 进件日期
                posTerminal.setImportDate(row.getCell(0).getDateCellValue());
                //下机日期
                posTerminal.setDownDate(row.getCell(1).getDateCellValue());
                // 装机日期
                posTerminal.setInstallDate(row.getCell(2).getDateCellValue());
                // 交件支行
                posTerminal.setInstallDate(row.getCell(3).getDateCellValue());
                // 机身号
                posTerminal.setDeviceNum(row.getCell(4).getStringCellValue());
                // 机子型号
                posTerminal.setDeviceType(row.getCell(5).getStringCellValue());
                // 商户号
                posTerminal.setMerchantNum(row.getCell(6).getStringCellValue());
                // 终端号
                posTerminal.setDeviceNum(row.getCell(7).getStringCellValue());
                // 微信二维码
                posTerminal.setWechatUrl(row.getCell(8).getStringCellValue());
                // todo 营业执照号码 这里是号码,不是URL
                posTerminal.setBusinessLicenseUrl(row.getCell(9).getStringCellValue());
                // 商户名称
                posTerminal.setMerchantName(row.getCell(10).getStringCellValue());
                // 地址
                posTerminal.setMerchantName(row.getCell(11).getStringCellValue());
                // 法人
                posTerminal.setMerchantLegalPerson(row.getCell(12).getStringCellValue());
                // 入账人
                posTerminal.setBookingPerson(row.getCell(13).getStringCellValue());
                // 联系电话
                posTerminal.setTelphone();
                // 装机电话
                posTerminal.setInstallPhone();
                // MCC码
                posTerminal.setDeviceMcc();
                // 借记卡费率
                posTerminal.setDebitRate();
                // 贷记卡费率
                posTerminal.setCreditRate();
                // 外币卡费率
                posTerminal.setForeignRate();
                // 机具类型
                posTerminal.set();
                // 身份证号码
                posTerminal.setIdCard();
                // 银行卡
                posTerminal.setBankCard();
                // 银行卡开户行
                posTerminal.setBankCardAccountBank();

                // 业务员
                posTerminal.setSalesman();
                // 详情
                posTerminal.setTerminalDesc();


                userPosTerminals.add(posTerminal);

                userMap.put(loginName, userPosTerminals);
            }

            // 插入机构\部门\用户
            Set<String> userLoginNames = userMap.keySet();
            // todo 获取根机构
            Office rootOffice = new Office();
            rootOffice.setId(Global.getRootOfficeId());

            for (String loginName : userLoginNames) {
                // 机构
                Office company = new Office();
                company.setName(loginName);
                officeService.save(company);
                // todo 所有上传的数据都是在[大创电子总公司之下]
                company.setParent(rootOffice);

                // 部门
                Office office = new Office();
                office.setName(loginName);
                office.setParent(company);
                officeService.save(office);

                // 用户
                User user = new User();
                user.setLoginName(loginName);
                user.setCompany(company);
                user.setOffice(office);
                systemService.saveUser(user);

                // 插入终端
                List<PosTerminal> terminals = userMap.get(loginName);
                for (PosTerminal posTerminal : terminals) {
                    // 后台编号,所属用户
                    posTerminal.setUser(user);
                }
                // 批量插入
                // terminalService.save(terminals);
            }


        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
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