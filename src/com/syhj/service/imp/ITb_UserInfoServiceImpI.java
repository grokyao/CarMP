package com.syhj.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.syhj.dao.ITb_UserInfo;
import com.syhj.dao.imp.ITb_UserInfoImpI;
import com.syhj.domain.Tb_UserInfo;
import com.syhj.service.ITb_UserInfoService;

public class ITb_UserInfoServiceImpI implements ITb_UserInfoService {
    public Connection conn1 = null;
    public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b = false;
    ITb_UserInfo myiTb_User = new ITb_UserInfoImpI();

    @Override
    public List<Tb_UserInfo> queryAllData() {
        return myiTb_User.queryAllData();
    }

    @Override
    public boolean insertData(Tb_UserInfo t) {
        if (t != null) {
            myiTb_User.insertData(t);
            b = true;
        }
        return b;
    }

    @Override
    public boolean update(Tb_UserInfo t) {
        if (t != null) {
            myiTb_User.update(t);
            b = true;
        }
        return b;
}

    @Override
    public boolean delete(int id) {
        if (id != 0) {
            myiTb_User.delete(id);
            b = true;
        }
        return b;
    }

    @Override
    public Tb_UserInfo queryDataById(int id) {
        if (id != 0) {
            return myiTb_User.queryDataById(id);
        } else {
            return null;
        }
    }
}
