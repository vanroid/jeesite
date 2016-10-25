package com.thinkgem.jeesite.modules.terminal.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * Created by cgz on 16-10-25.
 * POS终端实体
 */

public class PosTerminal {
    // 所属用户
    private User user;
    // 终端号
    private String terminalId;

    // other info


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }
}
